package es.ubu.ubulexa.controllers;

import es.ubu.ubulexa.Constants;
import java.util.HashMap;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@PetiteBean
public class WebAuthController {

  public ModelAndView get(Request req, Response res) {
    res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);

    return new ModelAndView(null, "webauth.ftl");
  }

  public ModelAndView post(Request req, Response res) {
    res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);

    String username = req.queryParams("username");
    String password = req.queryParams("password");

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("success", true);

    return new ModelAndView(attributes, "webauth.ftl");
  }
}
