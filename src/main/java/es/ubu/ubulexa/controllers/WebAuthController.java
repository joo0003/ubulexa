package es.ubu.ubulexa.controllers;

import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.tools.AccessTokenCreator;
import es.ubu.ubulexa.tools.MoodleTokenExchanger;
import es.ubu.ubulexa.tools.SystemEnvReader;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import es.ubu.ubulexa.utils.Base64Utils;
import es.ubu.ubulexa.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@PetiteBean
public class WebAuthController {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(WebAuthController.class);

  private MoodleTokenExchanger moodleTokenExchanger;
  private AccessTokenCreator accessTokenCreator;
  private JsonUtils jsonUtils;
  private AmazonS3Utils amazonS3Utils;
  private SystemEnvReader systemEnvReader;
  private Base64Utils base64Utils;

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  @PetiteInject
  public void setAmazonS3Utils(AmazonS3Utils amazonS3Utils) {
    this.amazonS3Utils = amazonS3Utils;
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

    String token = moodleTokenExchanger.exchange(username, password);

    if (StringUtils.isBlank(token)) {
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("error", true);
      attributes.put("state", state);
      attributes.put("redirectUri", redirectUri);

      res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);
      return new ModelAndView(attributes, "webauth.ftl");
    }

    String jwt = accessTokenCreator.create(username);

    redirectUri += "?state=" + state;
    redirectUri += "&code=" + jwt;

    dumpUserDataToS3(username, token);

    res.type(Constants.APPLICATION_JSON_MEDIA_TYPE);
    res.redirect(redirectUri);
    return null;
  }

  private void dumpUserDataToS3(String username, String token) {
    try {
      String filename = base64Utils.encode(username);

      String key = Constants.SUBFOLDER_USER_DATA_NAME + "/" + filename;
      amazonS3Utils.putObject(
          systemEnvReader.bucketName(),
          key,
          token
      );
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
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
