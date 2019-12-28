package es.ubu.ubulexa.core.matchers.intents;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.tools.sessionattributes.FirstTimeSessionAttributeGetter;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class FirstTimeIntentMatcher extends AbstractIntentMatcher {

  private FirstTimeSessionAttributeGetter firstTimeSessionAttributeGetter;

  @PetiteInject
  public void setFirstTimeSessionAttributeGetter(
      FirstTimeSessionAttributeGetter firstTimeSessionAttributeGetter) {
    this.firstTimeSessionAttributeGetter = firstTimeSessionAttributeGetter;
  }

  public boolean match(HandlerInput handlerInput) {
    return firstTimeSessionAttributeGetter.get(handlerInput);
  }
}