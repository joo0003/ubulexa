package es.ubu.ubulexa.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.Constants;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;

@PetiteBean
public class UserIdExtractor {

  private AccessTokenParser accessTokenParser;

  @PetiteInject
  public void setAccessTokenParser(AccessTokenParser accessTokenParser) {
    this.accessTokenParser = accessTokenParser;
  }

  public String extract(HandlerInput handlerInput) {
    String accessToken = extractAccessToken(handlerInput);
    if (StringUtils.isBlank(accessToken)) {
      return null;
    }

    DecodedJWT decodedJWT = accessTokenParser.parse(accessToken);
    if (null == decodedJWT) {
      return null;
    }
    return decodedJWT.getClaim(Constants.JWT_USERNAME_CLAIM).asString();
  }

  private String extractAccessToken(HandlerInput handlerInput) {
    return handlerInput.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
  }
}
