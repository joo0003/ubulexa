package es.ubu.ubulexa.events;

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import es.ubu.ubulexa.core.jobs.FirstTimeFilesJob;
import es.ubu.ubulexa.core.jobs.Job;
import es.ubu.ubulexa.core.jobs.ReqResFilesJob;
import es.ubu.ubulexa.core.jobs.TokenExchangeFilesJob;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
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
    run(TokenExchangeFilesJob.class);
    run(FirstTimeFilesJob.class);
    run(ReqResFilesJob.class);
  }

  private void run(Class<? extends Job> clazz) {
    try {
      petiteContainer.getBean(clazz).run();
    } catch (Exception e) {
      ExceptionUtils.log(e);
    }
  }
}