package es.ubu.ubulexa.core.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.tools.UserIdFactory;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserIdFetcher;
import es.ubu.ubulexa.core.tools.sessionattributes.MoodleTokenInvalidSessionAttributeSetter;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;

@PetiteBean
public class MoodleTokenValidityRequestInterceptorStep extends AbstractRequestInterceptorStep {

  private MoodleUserIdFetcher moodleUserIdFetcher;
  private MoodleTokenInvalidSessionAttributeSetter moodleTokenInvalidSessionAttributeSetter;
  private UserIdFactory userIdFactory;

  @PetiteInject
  public void setUserIdFactory(UserIdFactory userIdFactory) {
    this.userIdFactory = userIdFactory;
  }

  @PetiteInject
  public void setMoodleTokenInvalidSessionAttributeSetter(
      MoodleTokenInvalidSessionAttributeSetter moodleTokenInvalidSessionAttributeSetter) {
    this.moodleTokenInvalidSessionAttributeSetter = moodleTokenInvalidSessionAttributeSetter;
  }

  @PetiteInject
  public void setMoodleUserIdFetcher(
      MoodleUserIdFetcher moodleUserIdFetcher) {
    this.moodleUserIdFetcher = moodleUserIdFetcher;
  }

  @Override
  public void run(HandlerInput handlerInput) {
    String moodleToken = extractMoodleToken(handlerInput);
    String userId = findUserId(moodleToken);
    moodleTokenInvalidSessionAttributeSetter.set(handlerInput, StringUtil.isBlank(userId));
  }

  private String findUserId(String moodleToken) {
    String userId = userIdFactory.build(Constants.FAKE_AMAZON_UBU_EMAIL);
    if (StringUtil.equals(moodleToken, userId)) {
      return userId;
    }
    return moodleUserIdFetcher.fetch(moodleToken);
  }
}
