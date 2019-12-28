package es.ubu.ubulexa.core.responsefactories;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.response.ResponseBuilder;
import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.tools.DictionaryPropsResolver;
import es.ubu.ubulexa.core.tools.VoiceTransformerResolver;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import es.ubu.ubulexa.core.utils.HandlerInputUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;

@PetiteBean
public abstract class AbstractResponseFactory implements ResponseFactory {

  private DictionaryPropsResolver dictionaryPropsResolver;
  private AppConfig appConfig;
  private VoiceTransformerResolver voiceTransformerResolver;
  private HandlerInputUtils handlerInputUtils;

  @PetiteInject
  public void setHandlerInputUtils(HandlerInputUtils handlerInputUtils) {
    this.handlerInputUtils = handlerInputUtils;
  }

  @PetiteInject
  public void setVoiceTransformerResolver(
      VoiceTransformerResolver voiceTransformerResolver) {
    this.voiceTransformerResolver = voiceTransformerResolver;
  }

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setDictionaryPropsResolver(
      DictionaryPropsResolver dictionaryPropsResolver) {
    this.dictionaryPropsResolver = dictionaryPropsResolver;
  }

  protected Props resolveDictionary(HandlerInput handlerInput) {
    return dictionaryPropsResolver.resolve(handlerInput);
  }

  protected VoiceTransformer resolveVoiceTransformer(HandlerInput handlerInput) {
    return voiceTransformerResolver.resolve(handlerInput);
  }

  protected ResponseBuilder responseBuilder(
      HandlerInput handlerInput
  ) {
    return handlerInputUtils.getResponseBuilder(handlerInput);
  }
}
