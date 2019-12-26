package es.ubu.ubulexa.skill.requestinterceptors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.RequestInterceptor;
import es.ubu.ubulexa.skill.requestinterceptors.steps.UserEventsRequestInterceptorStep;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class MainRequestInterceptor implements RequestInterceptor {

  private PetiteContainer petiteContainer;

  @PetiteInject
  public void setPetiteContainer(PetiteContainer petiteContainer) {
    this.petiteContainer = petiteContainer;
  }

  @Override
  public void process(HandlerInput handlerInput) {
    petiteContainer.getBean(UserEventsRequestInterceptorStep.class).run(handlerInput);
  }
}
