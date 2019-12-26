package es.ubu.ubulexa.core.utils;

import java.util.Base64;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class Base64Utils {

  public String encode(String str) {
    return encode(str.getBytes());
  }

  public String encode(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }

  public byte[] decode(String str) {
    return Base64.getDecoder().decode(str);
  }
}
