package es.ubu.ubulexa.core.matchers.intents;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.tools.sessionattributes.MoodleTokenInvalidSessionAttributeGetter;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class MoodleTokenInvalidIntentMatcher extends AbstractIntentMatcher {

  private MoodleTokenInvalidSessionAttributeGetter moodleTokenInvalidSessionAttributeGetter;

  @PetiteInject
  public void setMoodleTokenInvalidSessionAttributeGetter(
      MoodleTokenInvalidSessionAttributeGetter moodleTokenInvalidSessionAttributeGetter) {
    this.moodleTokenInvalidSessionAttributeGetter = moodleTokenInvalidSessionAttributeGetter;
  }

  public boolean match(HandlerInput handlerInput) {
    return moodleTokenInvalidSessionAttributeGetter.get(handlerInput);
  }
}