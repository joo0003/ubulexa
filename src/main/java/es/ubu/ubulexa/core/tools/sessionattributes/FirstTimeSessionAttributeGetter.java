package es.ubu.ubulexa.core.tools.sessionattributes;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringPool;
import org.apache.commons.lang3.BooleanUtils;

@PetiteBean
public class FirstTimeSessionAttributeGetter extends AbstractSessionAttributeGetter {

  public boolean get(HandlerInput handlerInput) {
    Object o = getAll(handlerInput).get(Constants.FIRST_TIME_SESSION_ATTR_KEY);
    if (null == o) {
      return false;
    }
    return BooleanUtils.toBoolean(o.toString(), StringPool.TRUE, StringPool.FALSE);
  }
}
