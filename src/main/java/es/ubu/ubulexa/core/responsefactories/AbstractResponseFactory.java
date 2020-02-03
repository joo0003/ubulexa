package es.ubu.ubulexa.core.responsefactories;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.response.ResponseBuilder;
import es.ubu.ubulexa.core.tools.resolvers.DictionaryPropsResolver;
import es.ubu.ubulexa.core.tools.resolvers.VoiceTransformerResolver;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import es.ubu.ubulexa.core.utils.HandlerInputUtils;
import es.ubu.ubulexa.core.utils.time.InstantUtils;
import es.ubu.ubulexa.core.utils.time.LocalDateUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;

@PetiteBean
public abstract class AbstractResponseFactory implements ResponseFactory {

  private DictionaryPropsResolver dictionaryPropsResolver;
  private VoiceTransformerResolver voiceTransformerResolver;
  private HandlerInputUtils handlerInputUtils;
  private InstantUtils instantUtils;
  private LocalDateUtils localDateUtils;

  @PetiteInject
  public void setInstantUtils(InstantUtils instantUtils) {
    this.instantUtils = instantUtils;
  }

  @PetiteInject
  public void setLocalDateUtils(LocalDateUtils localDateUtils) {
    this.localDateUtils = localDateUtils;
  }

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
  public void setDictionaryPropsResolver(
      DictionaryPropsResolver dictionaryPropsResolver) {
    this.dictionaryPropsResolver = dictionaryPropsResolver;
  }

  protected LocalDateUtils localDateUtils() {
    return localDateUtils;
  }

  protected InstantUtils instantUtils() {
    return instantUtils;
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
