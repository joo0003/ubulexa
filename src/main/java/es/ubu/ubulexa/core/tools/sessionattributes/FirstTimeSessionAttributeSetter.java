package es.ubu.ubulexa.core.tools.sessionattributes;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringPool;
import org.apache.commons.lang3.BooleanUtils;

@PetiteBean
public class FirstTimeSessionAttributeSetter extends AbstractSessionAttributeSetter {

  public void set(HandlerInput handlerInput, boolean val) {
    String str = BooleanUtils.toString(val, StringPool.TRUE, StringPool.FALSE);
    setAll(handlerInput, Constants.FIRST_TIME_SESSION_ATTR_KEY, str);
  }
}
