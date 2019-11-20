package es.ubu.ubulexa.utils;

import java.util.UUID;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class UuidUtils {

  public String generate() {
    return UUID.randomUUID().toString();
  }
}
