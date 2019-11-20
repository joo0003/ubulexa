package es.ubu.ubulexa.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class GeolocationRequestInterceptorStep extends AbstractRequestInterceptorStep {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(GeolocationRequestInterceptorStep.class);

  public void run(HandlerInput handlerInput) {
    //throw new NotAGeolocationDeviceException();
    return;
  }
}
