package es.ubu.ubulexa.web.controllers;

import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@PetiteBean
public class PrivacyPolicyController extends AbstractController {

  public ModelAndView get(Request req, Response res) {
    res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);
    return new ModelAndView(null, "privacypolicy.ftl");
  }
}
