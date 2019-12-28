package es.ubu.ubulexa.core.tools.sessionattributes;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.utils.HandlerInputUtils;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractSessionAttributeGetter {

  private HandlerInputUtils handlerInputUtils;

  @PetiteInject
  public void setHandlerInputUtils(HandlerInputUtils handlerInputUtils) {
    this.handlerInputUtils = handlerInputUtils;
  }

  protected Map<String, Object> getAll(HandlerInput handlerInput) {
    return handlerInputUtils.getAttributesManager(handlerInput).getSessionAttributes();
  }
}
