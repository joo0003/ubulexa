package es.ubu.ubulexa.core.controllers;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.tools.AccessTokenCreator;
import es.ubu.ubulexa.core.tools.ThreefishEncrypter;
import es.ubu.ubulexa.core.tools.UserIdFactory;
import es.ubu.ubulexa.core.tools.moodle.MoodleTokenFetcher;
import es.ubu.ubulexa.core.tools.s3dumpers.TokenExchangeS3Dumper;
import es.ubu.ubulexa.core.utils.Base64Utils;
import es.ubu.ubulexa.core.utils.JsonUtils;
import es.ubu.ubulexa.core.utils.UrlUtils;
import es.ubu.ubulexa.core.utils.UuidUtils;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@PetiteBean
public class WebAuthController extends AbstractController {

  private UrlUtils urlUtils;
  private Base64Utils base64Utils;
  private MoodleTokenFetcher moodleTokenFetcher;
  private AccessTokenCreator accessTokenCreator;
  private JsonUtils jsonUtils;
  private ThreefishEncrypter threefishEncrypter;
  private UserIdFactory userIdFactory;
  private TokenExchangeS3Dumper tokenExchangeS3Dumper;
  private UuidUtils uuidUtils;

  @PetiteInject
  public void setUuidUtils(UuidUtils uuidUtils) {
    this.uuidUtils = uuidUtils;
  }

  @PetiteInject
  public void setTokenExchangeS3Dumper(
      TokenExchangeS3Dumper tokenExchangeS3Dumper) {
    this.tokenExchangeS3Dumper = tokenExchangeS3Dumper;
  }

  @PetiteInject
  public void setUserIdFactory(UserIdFactory userIdFactory) {
    this.userIdFactory = userIdFactory;
  }

  @PetiteInject
  public void setUrlUtils(UrlUtils urlUtils) {
    this.urlUtils = urlUtils;
  }

  @PetiteInject
  public void setThreefishEncrypter(ThreefishEncrypter threefishEncrypter) {
    this.threefishEncrypter = threefishEncrypter;
  }

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  @PetiteInject
  public void setJsonUtils(JsonUtils jsonUtils) {
    this.jsonUtils = jsonUtils;
  }

  @PetiteInject
  public void setAccessTokenCreator(AccessTokenCreator accessTokenCreator) {
    this.accessTokenCreator = accessTokenCreator;
  }

  @PetiteInject
  public void setMoodleTokenFetcher(MoodleTokenFetcher moodleTokenFetcher) {
    this.moodleTokenFetcher = moodleTokenFetcher;
  }

  public ModelAndView get(Request req, Response res) {
    res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);

    String state = req.queryParams("state");
    String redirectUri = req.queryParams("redirect_uri");

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("state", state);
    attributes.put("redirectUri", redirectUri);

    return new ModelAndView(attributes, "webauth.ftl");
  }

  public ModelAndView post(Request req, Response res) {
    String username = StringUtils.defaultString(req.queryParams("username"));
    String password = StringUtils.defaultString(req.queryParams("password"));
    String state = StringUtils.defaultString(req.queryParams("state"));
    String redirectUri = StringUtils.defaultString(req.queryParams("redirectUri"));

    String moodleToken = moodleTokenFetcher.fetch(username, password);

    if (StringUtils.isBlank(moodleToken)) {
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("error", true);
      attributes.put("state", state);
      attributes.put("redirectUri", redirectUri);

      res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);
      return new ModelAndView(attributes, "webauth.ftl");
    }

    String userId = userIdFactory.build(username);

    String jwt = accessTokenCreator.create(userId, username, moodleToken);

    byte[] encryptedJwt = threefishEncrypter.encrypt(appConfig().threefishSecret(), jwt);

    String base64EncryptedJwt = base64Utils.encode(encryptedJwt);

    String code = urlUtils.encodeQueryParam(base64EncryptedJwt);

    redirectUri += "?state=" + state;
    redirectUri += "&code=" + code;

    res.type(Constants.APPLICATION_JSON_MEDIA_TYPE);
    res.redirect(redirectUri);
    return null;
  }

  public String postToken(Request req, Response res) {
    res.type(Constants.APPLICATION_JSON_MEDIA_TYPE);

    long seconds = 10 * 365 * 24 * 60 * 60;

    String code = req.queryParams("code");

    Map<String, Object> map = new HashMap<>();
    map.put("access_token", code);
    map.put("token_type", "bearer");
    map.put("expires_in", seconds);
    map.put("refresh_token", StringUtil.reverse(code));

    String jsonStr = jsonUtils.jsonSerializer().serialize(map);

    tokenExchangeS3Dumper.dump(
        uuidUtils.generate(),
        jsonStr.getBytes(StandardCharsets.UTF_8)
    );

    return jsonStr;
  }
}
