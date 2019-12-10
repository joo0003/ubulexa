package es.ubu.ubulexa.requestinterceptors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.RequestInterceptor;
import es.ubu.ubulexa.requestinterceptors.steps.UserEventsRequestInterceptorStep;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class MainRequestInterceptor implements RequestInterceptor {

  private UserEventsRequestInterceptorStep userEventsRequestInterceptorStep;

  @PetiteInject
  public void setUserEventsRequestInterceptorStep(
      UserEventsRequestInterceptorStep userEventsRequestInterceptorStep) {
    this.userEventsRequestInterceptorStep = userEventsRequestInterceptorStep;
  }

  @Override
  public void process(HandlerInput handlerInput) {
    userEventsRequestInterceptorStep.run(handlerInput);
  }
}
