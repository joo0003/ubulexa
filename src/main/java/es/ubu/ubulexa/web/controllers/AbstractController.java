package es.ubu.ubulexa.web.controllers;

import es.ubu.ubulexa.core.tools.AppConfig;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractController {

  private AppConfig appConfig;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  public AppConfig appConfig() {
    return appConfig;
  }
}
