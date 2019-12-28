package es.ubu.ubulexa.core.controllers;

import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;
import spark.Request;
import spark.Response;

@PetiteBean
public class HealthController extends AbstractController {

  public String get(Request req, Response res) {
    res.type(Constants.APPLICATION_PLAIN_TEXT_MEDIA_TYPE);
    return "great scott!";
  }
}
