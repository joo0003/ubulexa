package es.ubu.ubulexa.core.tools.resolvers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.matchers.intents.CalendarIntentMatcher;
import es.ubu.ubulexa.core.matchers.intents.CancelIntentMatcher;
import es.ubu.ubulexa.core.matchers.intents.FallbackIntentMatcher;
import es.ubu.ubulexa.core.matchers.intents.FirstTimeIntentMatcher;
import es.ubu.ubulexa.core.matchers.intents.HelpIntentMatcher;
import es.ubu.ubulexa.core.matchers.intents.MoodleTokenInvalidIntentMatcher;
import es.ubu.ubulexa.core.matchers.intents.NavigateHomeIntentMatcher;
import es.ubu.ubulexa.core.matchers.intents.StopIntentMatcher;
import es.ubu.ubulexa.core.matchers.requests.LaunchRequestMatcher;
import es.ubu.ubulexa.core.matchers.requests.SessionEndedRequestMatcher;
import es.ubu.ubulexa.core.responsefactories.CalendarResponseFactory;
import es.ubu.ubulexa.core.responsefactories.FallbackResponseFactory;
import es.ubu.ubulexa.core.responsefactories.FirstTimeResponseFactory;
import es.ubu.ubulexa.core.responsefactories.GoodByeResponseFactory;
import es.ubu.ubulexa.core.responsefactories.HelpResponseFactory;
import es.ubu.ubulexa.core.responsefactories.MoodleTokenInvalidResponseFactory;
import es.ubu.ubulexa.core.responsefactories.ResponseFactory;
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

  public ResponseFactory resolve(HandlerInput handlerInput) {
    if (null == handlerInput) {
      return null;
    }

    // KEEP IT FIRST ALWAYS
    if (isMoodleTokenInvalidIntent(handlerInput)) {
      return petiteContainer.getBean(MoodleTokenInvalidResponseFactory.class);
    }

    // KEEP IT SECOND ALWAYS
    if (isFirstTimeIntent(handlerInput)) {
      return petiteContainer.getBean(FirstTimeResponseFactory.class);
    }

    if (isCancelIntent(handlerInput)) {
      return petiteContainer.getBean(GoodByeResponseFactory.class);
    }

    if (isHelpIntent(handlerInput)) {
      return petiteContainer.getBean(HelpResponseFactory.class);
    }

    if (isStopIntent(handlerInput)) {
      return petiteContainer.getBean(GoodByeResponseFactory.class);
    }

    if (isNavigateHomeIntent(handlerInput)) {
      return petiteContainer.getBean(GoodByeResponseFactory.class);
    }

    if (isFallbackIntent(handlerInput)) {
      return petiteContainer.getBean(FallbackResponseFactory.class);
    }

    if (isLaunchRequest(handlerInput)) {
      return petiteContainer.getBean(CalendarResponseFactory.class);
    }

    if (isSessionEndedRequest(handlerInput)) {
      return petiteContainer.getBean(GoodByeResponseFactory.class);
    }

    if (isCalendarIntent(handlerInput)) {
      return petiteContainer.getBean(CalendarResponseFactory.class);
    }

    return null;
  }

  private boolean isMoodleTokenInvalidIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(MoodleTokenInvalidIntentMatcher.class).match(handlerInput);
  }

  private boolean isSessionEndedRequest(HandlerInput handlerInput) {
    return petiteContainer.getBean(SessionEndedRequestMatcher.class).match(handlerInput);
  }

  private boolean isLaunchRequest(HandlerInput handlerInput) {
    return petiteContainer.getBean(LaunchRequestMatcher.class).match(handlerInput);
  }

  private boolean isFirstTimeIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(FirstTimeIntentMatcher.class).match(handlerInput);
  }

  private boolean isCalendarIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(CalendarIntentMatcher.class).match(handlerInput);
  }

  private boolean isFallbackIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(FallbackIntentMatcher.class).match(handlerInput);
  }

  private boolean isNavigateHomeIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(NavigateHomeIntentMatcher.class).match(handlerInput);
  }

  private boolean isStopIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(StopIntentMatcher.class).match(handlerInput);
  }

  private boolean isHelpIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(HelpIntentMatcher.class).match(handlerInput);
  }

  private boolean isCancelIntent(HandlerInput handlerInput) {
    return petiteContainer.getBean(CancelIntentMatcher.class).match(handlerInput);
  }
}
