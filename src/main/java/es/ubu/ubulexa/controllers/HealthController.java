package es.ubu.ubulexa.controllers;

import es.ubu.ubulexa.Constants;
import jodd.petite.meta.PetiteBean;
import spark.Request;
import spark.Response;

@PetiteBean
public class HealthController {

  public String get(Request req, Response res) {
    res.type(Constants.APPLICATION_PLAIN_TEXT_MEDIA_TYPE);
    return "great scott!";
  }
}
