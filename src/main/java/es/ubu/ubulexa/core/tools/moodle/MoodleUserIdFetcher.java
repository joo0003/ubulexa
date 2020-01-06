package es.ubu.ubulexa.core.tools.moodle;

import jodd.json.JsonObject;
import jodd.petite.meta.PetiteBean;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class MoodleUserIdFetcher extends AbstractMoodleFetcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(MoodleUserIdFetcher.class);

  public String fetch(String moodleToken) {
    try {
      String response = moodleHttpClient().getSiteInfo(moodleToken);

      JsonObject jsonObject = jsonUtils().jsonParser().parseAsJsonObject(response);

      Integer userId = jsonObject.getInteger("userid", null);
      return null == userId ? null : userId.toString();
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      return null;
    }
  }
}
