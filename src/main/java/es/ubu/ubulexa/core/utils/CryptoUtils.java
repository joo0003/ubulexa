package es.ubu.ubulexa.core.utils;

import jodd.crypt.CryptoEngine;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class CryptoUtils {

  public CryptoEngine threefish(String secret) {
    return CryptoEngine.threefish(secret);
  }
}
