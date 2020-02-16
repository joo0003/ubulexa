package es.ubu.ubulexa.events;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import es.ubu.ubulexa.events.config.petite.PetiteInitializer;
import es.ubu.ubulexa.events.config.petite.PetitePostInitializer;
import jodd.petite.PetiteContainer;

public class ScheduledEventHandler implements RequestHandler<ScheduledEvent, String> {

  private static PetiteContainer petiteContainer;

  public ScheduledEventHandler() {
    try {
      if (null == petiteContainer) {
        petiteContainer = PetiteInitializer.init();
        PetitePostInitializer.init(petiteContainer);
      }
    } catch (Exception e) {
      ExceptionUtils.log(e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public String handleRequest(ScheduledEvent scheduledEvent, Context context) {
    ScheduledEventHandlerModel handlerModel = petiteContainer
        .getBean(ScheduledEventHandlerModel.class);
    handlerModel.handleRequest(scheduledEvent);
    return null;
  }
}