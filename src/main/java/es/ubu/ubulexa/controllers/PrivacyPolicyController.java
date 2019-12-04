package es.ubu.ubulexa.controllers;

import es.ubu.ubulexa.Constants;
import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@PetiteBean
public class PrivacyPolicyController {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(PrivacyPolicyController.class);

  public ModelAndView get(Request req, Response res) {
    res.type(Constants.APPLICATION_HTML_MEDIA_TYPE);

    return new ModelAndView(null, "privacypolicy.ftl");
  }
}
