package es.ubu.ubulexa.core.tools.moodle;

import es.ubu.ubulexa.core.utils.ExceptionUtils;
import java.util.HashSet;
import java.util.Set;
import jodd.json.JsonArray;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class MoodleUserCoursesFetcher extends AbstractMoodleFetcher {

  public Set<String> fetch(String moodleToken, String userId) {
    Set<String> courseIds = new HashSet<>();

    try {
      String response = moodleHttpClient().getUserCourses(moodleToken, userId);

      JsonArray jsonArray = jsonUtils().jsonParser().parseAsJsonArray(response);

      if (jsonArray.isEmpty()) {
        return courseIds;
      }

      for (int i = 0; i < jsonArray.size(); i++) {
        Integer courseId = jsonArray.getJsonObject(i).getInteger("id", null);
        if (null != courseId) {
          courseIds.add(courseId.toString());
        }
      }

      courseIds.remove(null);

    } catch (Exception e) {
      ExceptionUtils.log(e);
    }

    return courseIds;
  }
}
