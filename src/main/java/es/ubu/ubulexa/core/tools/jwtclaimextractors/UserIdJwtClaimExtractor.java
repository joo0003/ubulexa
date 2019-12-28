package es.ubu.ubulexa.core.tools.jwtclaimextractors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class UserIdJwtClaimExtractor extends AbstractJwtClaimExtractor {

  @Override
  public String extract(HandlerInput handlerInput) {
    DecodedJWT decodedJWT = decodeJwt(handlerInput);

    if (null == decodedJWT) {
      return null;
    }

    return decodedJWT.getClaim(Constants.JWT_USER_ID_CLAIM).asString();
  }
}
