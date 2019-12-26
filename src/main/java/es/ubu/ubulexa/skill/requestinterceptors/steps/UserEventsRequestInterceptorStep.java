package es.ubu.ubulexa.skill.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.daos.FirstTimeUserEventDao;
import es.ubu.ubulexa.core.exceptions.NoFirstTimeUserEventFoundException;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class UserEventsRequestInterceptorStep extends AbstractRequestInterceptorStep {

  private FirstTimeUserEventDao firstTimeUserEventDao;

  @PetiteInject
  public void setFirstTimeUserEventDao(FirstTimeUserEventDao firstTimeUserEventDao) {
    this.firstTimeUserEventDao = firstTimeUserEventDao;
  }

  @Override
  public void run(HandlerInput handlerInput) {
    String userId = extractUserId(handlerInput);
    if (!firstTimeUserEventDao.exists(userId)) {
      throw new NoFirstTimeUserEventFoundException();
    }
  }
}
