package es.ubu.ubulexa.web.controllers;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.tools.AccessTokenCreator;
import es.ubu.ubulexa.core.tools.MoodleTokenExchanger;
import es.ubu.ubulexa.core.tools.ThreefishEncrypter;
import es.ubu.ubulexa.core.utils.Base64Utils;
import es.ubu.ubulexa.core.utils.JsonUtils;
import es.ubu.ubulexa.core.utils.UrlUtils;
import es.ubu.ubulexa.core.utils.UuidUtils;
import java.util.HashMap;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@PetiteBean
public class WebAuthController extends AbstractController {

  private UrlUtils urlUtils;
  private Base64Utils base64Utils;
  private MoodleTokenExchanger moodleTokenExchanger;
  private AccessTokenCreator accessTokenCreator;
  private JsonUtils jsonUtils;
  private ThreefishEncrypter threefishEncrypter;
  private UuidUtils uuidUtils;

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
  public void setUuidUtils(UuidUtils uuidUtils) {
    this.uuidUtils = uuidUtils;
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
  public void setMoodleTokenExchanger(MoodleTokenExchanger moodleTokenExchanger) {
    this.moodleTokenExchanger = moodleTokenExchanger;
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

    String moodleToken = moodleTokenExchanger.exchange(username, password);

    if (StringUtils.isBlank(moodleToken)) {
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("error", true);
      attributes.put("state", state);
      attributes.put("redirectUri", redirectUri);

      res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);
      return new ModelAndView(attributes, "webauth.ftl");
    }

    String userId = base64Utils.encode(username);

    String jwt = accessTokenCreator.create(userId, username, moodleToken);

    byte[] encryptedJwt = threefishEncrypter.encrypt(appConfig().threefishSecret(), jwt);

    String code = urlUtils.encodeQueryParam(base64Utils.encode(encryptedJwt));

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
    map.put("refresh_token", code);

    return jsonUtils.jsonSerializer().serialize(map);
  }
}
