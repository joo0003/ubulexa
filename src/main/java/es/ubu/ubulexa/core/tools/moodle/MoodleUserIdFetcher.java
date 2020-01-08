package es.ubu.ubulexa.core.tools.moodle;

import es.ubu.ubulexa.core.utils.ExceptionUtils;
import jodd.json.JsonObject;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class MoodleUserIdFetcher extends AbstractMoodleFetcher {

  public String fetch(String moodleToken) {
    try {
      String response = moodleHttpClient().getSiteInfo(moodleToken);

      JsonObject jsonObject = jsonUtils().jsonParser().parseAsJsonObject(response);

      Integer userId = jsonObject.getInteger("userid", null);
      return null == userId ? null : userId.toString();
    } catch (Exception e) {
      ExceptionUtils.log(e);
      return null;
    }
  }
}
