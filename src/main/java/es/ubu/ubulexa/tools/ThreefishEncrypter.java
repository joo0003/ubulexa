package es.ubu.ubulexa.tools;

import es.ubu.ubulexa.utils.CryptoUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class ThreefishEncrypter {

  private CryptoUtils cryptoUtils;

  @PetiteInject
  public void setCryptoUtils(CryptoUtils cryptoUtils) {
    this.cryptoUtils = cryptoUtils;
  }

  public byte[] encrypt(String secret, String str) {
    return cryptoUtils.threefish(secret).encryptString(str);
  }
}
