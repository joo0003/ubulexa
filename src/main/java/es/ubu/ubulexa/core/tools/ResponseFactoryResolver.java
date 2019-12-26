package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import es.ubu.ubulexa.skill.handlers.HandlerModel;
import es.ubu.ubulexa.skill.handlers.exceptions.models.NoFirstTimeUserEventFoundExceptionHandlerModel;
import es.ubu.ubulexa.skill.handlers.intents.models.CalendarIntentHandlerModel;
import es.ubu.ubulexa.skill.handlers.intents.models.CancelIntentHandlerModel;
import es.ubu.ubulexa.skill.handlers.intents.models.FallbackIntentHandlerModel;
import es.ubu.ubulexa.skill.handlers.intents.models.HelpIntentHandlerModel;
import es.ubu.ubulexa.skill.handlers.intents.models.StopIntentHandlerModel;
import es.ubu.ubulexa.skill.handlers.intents.models.VoiceChangeIntentHandlerModel;
import es.ubu.ubulexa.skill.handlers.requesthandlers.models.LaunchRequestHandlerModel;
import es.ubu.ubulexa.skill.responsefactories.ResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.exceptions.NoFirstTimeUserEventFoundExceptionResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.intents.CalendarIntentResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.intents.CancelIntentResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.intents.FallbackIntentResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.intents.HelpIntentResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.intents.StopIntentResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.intents.VoiceChangeIntentResponseFactory;
import es.ubu.ubulexa.skill.responsefactories.requests.LaunchRequestResponseFactory;
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

    if (isNoFirstTimeUserEventFoundExceptionHandlerModelClass(responseTemplate.getClazz())) {
      return petiteContainer.getBean(NoFirstTimeUserEventFoundExceptionResponseFactory.class);
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

    return null;
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

  private boolean isNoFirstTimeUserEventFoundExceptionHandlerModelClass(
      Class<? extends HandlerModel> clazz
  ) {
    return clazz.isAssignableFrom(NoFirstTimeUserEventFoundExceptionHandlerModel.class);
  }
}
