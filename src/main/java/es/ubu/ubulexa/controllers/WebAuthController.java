package es.ubu.ubulexa.controllers;

import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.tools.AccessTokenCreator;
import es.ubu.ubulexa.tools.MoodleTokenExchanger;
import es.ubu.ubulexa.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@PetiteBean
public class WebAuthController {

  private MoodleTokenExchanger moodleTokenExchanger;
  private AccessTokenCreator accessTokenCreator;
  private JsonUtils jsonUtils;

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

    String jwt = accessTokenCreator.create(username, token);

    redirectUri += "?state=" + state;
    redirectUri += "&code=" + jwt;

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
