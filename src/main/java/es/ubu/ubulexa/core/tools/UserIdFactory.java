package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.utils.Base64Utils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class UserIdFactory {

  private Base64Utils base64Utils;

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  public String build(String username) {
    return base64Utils.encode(username);
  }
}
