package es.ubu.ubulexa.core.tools.resolvers;

import es.ubu.ubulexa.core.handlers.HandlerModel;
import es.ubu.ubulexa.core.handlers.intents.models.CalendarIntentHandlerModel;
import es.ubu.ubulexa.core.handlers.intents.models.CancelIntentHandlerModel;
import es.ubu.ubulexa.core.handlers.intents.models.FallbackIntentHandlerModel;
import es.ubu.ubulexa.core.handlers.intents.models.FirstTimeIntentHandlerModel;
import es.ubu.ubulexa.core.handlers.intents.models.HelpIntentHandlerModel;
import es.ubu.ubulexa.core.handlers.intents.models.StopIntentHandlerModel;
import es.ubu.ubulexa.core.handlers.intents.models.VoiceChangeIntentHandlerModel;
import es.ubu.ubulexa.core.handlers.requesthandlers.models.LaunchRequestHandlerModel;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import es.ubu.ubulexa.core.responsefactories.ResponseFactory;
import es.ubu.ubulexa.core.responsefactories.intents.CalendarIntentResponseFactory;
import es.ubu.ubulexa.core.responsefactories.intents.CancelIntentResponseFactory;
import es.ubu.ubulexa.core.responsefactories.intents.FallbackIntentResponseFactory;
import es.ubu.ubulexa.core.responsefactories.intents.FirstTimeIntentResponseFactory;
import es.ubu.ubulexa.core.responsefactories.intents.HelpIntentResponseFactory;
import es.ubu.ubulexa.core.responsefactories.intents.StopIntentResponseFactory;
import es.ubu.ubulexa.core.responsefactories.intents.VoiceChangeIntentResponseFactory;
import es.ubu.ubulexa.core.responsefactories.requests.LaunchRequestResponseFactory;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class ResponseFactoryResolver {

  private PetiteContainer petiteContainer;

  @PetiteInject
  public void setPetiteContainer(PetiteContainer petiteContainer) {
    this.petiteContainer = petiteContainer;
  }

  public ResponseFactory resolve(ResponseTemplate responseTemplate) {
    if (null == responseTemplate) {
      return null;
    }

    if (null == responseTemplate.getClazz()) {
      return null;
    }

    if (isCancelIntentHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(CancelIntentResponseFactory.class);
    }

    if (isStopIntentHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(StopIntentResponseFactory.class);
    }

    if (isVoiceChangeIntentHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(VoiceChangeIntentResponseFactory.class);
    }

    if (isFallbackIntentHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(FallbackIntentResponseFactory.class);
    }

    if (isHelpIntentHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(HelpIntentResponseFactory.class);
    }

    if (isLaunchRequestHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(LaunchRequestResponseFactory.class);
    }

    if (isCalendarIntentHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(CalendarIntentResponseFactory.class);
    }

    if (isFirstTimeIntentHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(FirstTimeIntentResponseFactory.class);
    }

    return null;
  }

  private boolean isFirstTimeIntentHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(FirstTimeIntentHandlerModel.class);
  }

  private boolean isCalendarIntentHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(CalendarIntentHandlerModel.class);
  }

  private boolean isLaunchRequestHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(LaunchRequestHandlerModel.class);
  }

  private boolean isHelpIntentHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(HelpIntentHandlerModel.class);
  }

  private boolean isFallbackIntentHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(FallbackIntentHandlerModel.class);
  }

  private boolean isVoiceChangeIntentHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(VoiceChangeIntentHandlerModel.class);
  }

  private boolean isCancelIntentHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(CancelIntentHandlerModel.class);
  }

  private boolean isStopIntentHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(StopIntentHandlerModel.class);
  }
}
