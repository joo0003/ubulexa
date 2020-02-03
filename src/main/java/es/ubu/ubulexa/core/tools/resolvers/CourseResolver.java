package es.ubu.ubulexa.core.tools.resolvers;

import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserCoursesFetcher;
import java.util.HashSet;
import java.util.Set;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CourseResolver {

  private MoodleUserCoursesFetcher moodleUserCoursesFetcher;
  private AppConfig appConfig;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setMoodleUserCoursesFetcher(
      MoodleUserCoursesFetcher moodleUserCoursesFetcher) {
    this.moodleUserCoursesFetcher = moodleUserCoursesFetcher;
  }

  public Set<String> resolve(String moodleToken, String userId) {
    Set<String> courses = moodleUserCoursesFetcher.fetch(moodleToken, userId);

    if (CollectionUtils.isEmpty(courses)) {
      return new HashSet<>();
    }

    courses.remove(null);

    Set<String> set = new HashSet<>();
    for (String course : courses) {
      if (StringUtil.equals(course, appConfig.cheloCourse1Id())) {
        set.add(course);
      } else if (StringUtil.equals(course, appConfig.cheloCourse2Id())) {
        set.add(course);
      } else if (StringUtil.equals(course, appConfig.raulCourse1())) {
        set.add(course);
      }
    }
    return set;
  }
}
