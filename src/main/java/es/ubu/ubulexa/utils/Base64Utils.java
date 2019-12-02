package es.ubu.ubulexa.utils;

import java.util.Base64;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class Base64Utils {

  public String encode(String str) {
    return Base64.getEncoder().encodeToString(str.getBytes());
  }
}
