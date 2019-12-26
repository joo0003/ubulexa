package es.ubu.ubulexa.core.tools.jwtclaimextractors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.core.tools.JwtDecoder;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractJwtClaimExtractor {

  private JwtDecoder jwtDecoder;

  @PetiteInject
  public void setJwtDecoder(JwtDecoder jwtDecoder) {
    this.jwtDecoder = jwtDecoder;
  }

  protected DecodedJWT decodeJwt(HandlerInput handlerInput) {
    return jwtDecoder.decode(handlerInput);
  }

  public abstract String extract(HandlerInput handlerInput);
}
