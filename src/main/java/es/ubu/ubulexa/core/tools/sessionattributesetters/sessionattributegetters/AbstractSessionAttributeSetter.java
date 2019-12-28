package es.ubu.ubulexa.core.tools.sessionattributesetters.sessionattributegetters;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.utils.HandlerInputUtils;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractSessionAttributeSetter {

  private HandlerInputUtils handlerInputUtils;

  @PetiteInject
  public void setHandlerInputUtils(HandlerInputUtils handlerInputUtils) {
    this.handlerInputUtils = handlerInputUtils;
  }

  protected void setAll(
      HandlerInput handlerInput,
      String key,
      String val
  ) {
    AttributesManager attributesManager = handlerInputUtils.getAttributesManager(handlerInput);
    Map<String, Object> map = attributesManager.getSessionAttributes();
    map.put(key, val);
    attributesManager.setSessionAttributes(map);
  }
}
