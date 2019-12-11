package es.ubu.ubulexa.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.utils.Base64Utils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;

@PetiteBean
public class UserIdExtractor {

  private AccessTokenParser accessTokenParser;
  private ThreefishDecrypter threefishDecrypter;
  private Base64Utils base64Utils;
  private SystemEnvReader systemEnvReader;

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  @PetiteInject
  public void setThreefishDecrypter(ThreefishDecrypter threefishDecrypter) {
    this.threefishDecrypter = threefishDecrypter;
  }

  @PetiteInject
  public void setAccessTokenParser(AccessTokenParser accessTokenParser) {
    this.accessTokenParser = accessTokenParser;
  }

  public String extract(HandlerInput handlerInput) {
    String code = extractAccessToken(handlerInput);
    if (StringUtils.isBlank(code)) {
      return null;
    }

    byte[] encryptedJwt = base64Utils.decode(code);

    String decryptedJwt = threefishDecrypter.decrypt(
        systemEnvReader.threefishSecret(),
        encryptedJwt
    );

    DecodedJWT decodedJWT = accessTokenParser.parse(decryptedJwt);
    if (null == decodedJWT) {
      return null;
    }

    return decodedJWT.getClaim(Constants.JWT_USERNAME_CLAIM).asString();
  }

  private String extractAccessToken(HandlerInput handlerInput) {
    return handlerInput.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
  }
}
