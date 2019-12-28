package es.ubu.ubulexa.core.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.core.utils.Base64Utils;
import es.ubu.ubulexa.core.utils.HandlerInputUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;

@PetiteBean
public class JwtDecoder {

  private AccessTokenParser accessTokenParser;
  private ThreefishDecrypter threefishDecrypter;
  private Base64Utils base64Utils;
  private AppConfig appConfig;
  private HandlerInputUtils handlerInputUtils;

  @PetiteInject
  public void setHandlerInputUtils(HandlerInputUtils handlerInputUtils) {
    this.handlerInputUtils = handlerInputUtils;
  }

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
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

  public DecodedJWT decode(HandlerInput handlerInput) {
    String code = extractAccessToken(handlerInput);
    if (StringUtils.isBlank(code)) {
      return null;
    }

    byte[] encryptedJwt = base64Utils.decode(code);

    String decryptedJwt = threefishDecrypter.decrypt(
        appConfig.threefishSecret(),
        encryptedJwt
    );

    return accessTokenParser.parse(decryptedJwt);
  }

  private String extractAccessToken(HandlerInput handlerInput) {
    return handlerInputUtils.getRequestEnvelope(handlerInput)
        .getContext()
        .getSystem()
        .getUser()
        .getAccessToken();
  }
}
