package es.ubu.ubulexa.core.utils;

import jodd.petite.meta.PetiteBean;
import spark.template.freemarker.FreeMarkerEngine;

@PetiteBean
public class FreemarkerUtils {

  public FreeMarkerEngine engine() {
    return new FreeMarkerEngine();
  }
}
