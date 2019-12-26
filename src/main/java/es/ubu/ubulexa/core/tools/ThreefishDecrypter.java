package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.utils.CryptoUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class ThreefishDecrypter {

  private CryptoUtils cryptoUtils;

  @PetiteInject
  public void setCryptoUtils(CryptoUtils cryptoUtils) {
    this.cryptoUtils = cryptoUtils;
  }

  public String decrypt(String secret, byte[] bytes) {
    return cryptoUtils.threefish(secret).decryptString(bytes);
  }
}
