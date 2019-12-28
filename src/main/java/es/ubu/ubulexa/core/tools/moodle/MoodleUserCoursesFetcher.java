package es.ubu.ubulexa.core.tools.moodle;

import java.util.HashSet;
import java.util.Set;
import jodd.json.JsonArray;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringPool;
import jodd.util.StringUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class MoodleUserCoursesFetcher extends AbstractMoodleFetcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(MoodleUserCoursesFetcher.class);

  public Set<String> fetch(String moodleToken, String userId) {
    Set<String> courseIds = new HashSet<>();

    try {
      String response = moodleHttpClient().getUserCourses(moodleToken, userId);

      JsonArray jsonArray = jsonUtils().jsonParser().parseAsJsonArray(response);

      if (jsonArray.isEmpty()) {
        return courseIds;
      }

      for (int i = 0; i < jsonArray.size(); i++) {
        String courseId = jsonArray.getJsonObject(i).getString("id", StringPool.EMPTY);
        courseId = StringUtil.trimDown(courseId);
        courseIds.add(courseId);
      }

      courseIds.remove(null);

    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }

    return courseIds;
  }
}
