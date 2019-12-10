package es.ubu.ubulexa.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.daos.FirstTimeUserEventDao;
import es.ubu.ubulexa.exceptions.NoFirstTimeUserEventFoundException;
import es.ubu.ubulexa.tools.UserIdExtractor;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class UserEventsRequestInterceptorStep extends AbstractRequestInterceptorStep {

  private UserIdExtractor userIdExtractor;
  private FirstTimeUserEventDao firstTimeUserEventDao;

  @PetiteInject
  public void setUserIdExtractor(UserIdExtractor userIdExtractor) {
    this.userIdExtractor = userIdExtractor;
  }

  @PetiteInject
  public void setFirstTimeUserEventDao(FirstTimeUserEventDao firstTimeUserEventDao) {
    this.firstTimeUserEventDao = firstTimeUserEventDao;
  }

  public void run(HandlerInput handlerInput) {
    String userId = userIdExtractor.extract(handlerInput);
    if (!firstTimeUserEventDao.exists(userId)) {
      throw new NoFirstTimeUserEventFoundException();
    }
  }
}
