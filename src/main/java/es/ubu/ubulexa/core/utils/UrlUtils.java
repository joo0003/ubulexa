package es.ubu.ubulexa.core.utils;

import jodd.net.URLCoder;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class UrlUtils {

  public String encodeQueryParam(String str) {
    return URLCoder.encodeQueryParam(str);
  }
}
