package es.ubu.ubulexa.events;

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import es.ubu.ubulexa.core.jobs.TokenExchangeFilesJob;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class ScheduledEventHandlerModel {

  private PetiteContainer petiteContainer;

  @PetiteInject
  public void setPetiteContainer(PetiteContainer petiteContainer) {
    this.petiteContainer = petiteContainer;
  }

  public void handleRequest(ScheduledEvent scheduledEvent) {
    petiteContainer.getBean(TokenExchangeFilesJob.class).run();
  }
}