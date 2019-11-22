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

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("message", "Hello World!");

    return new ModelAndView(attributes, "webauth.ftl");
  }
}
