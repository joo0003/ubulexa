package es.ubu.ubulexa.requestinterceptors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.RequestInterceptor;
import es.ubu.ubulexa.requestinterceptors.steps.GeolocationRequestInterceptorStep;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class MainRequestInterceptor implements RequestInterceptor {

  private GeolocationRequestInterceptorStep geolocationRequestInterceptorStep;

  @PetiteInject
  public void setGeolocationRequestInterceptorStep(
      GeolocationRequestInterceptorStep geolocationRequestInterceptorStep) {
    this.geolocationRequestInterceptorStep = geolocationRequestInterceptorStep;
  }

  @Override
  public void process(HandlerInput handlerInput) {
    //geolocationRequestInterceptorStep.run(handlerInput);
  }
}
