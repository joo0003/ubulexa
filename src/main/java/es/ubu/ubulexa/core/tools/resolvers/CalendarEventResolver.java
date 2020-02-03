package es.ubu.ubulexa.core.tools.resolvers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.pojos.CalendarEventResolverResult;
import es.ubu.ubulexa.core.tools.CalendarJsonReader;
import es.ubu.ubulexa.core.tools.filters.calendarevents.CalendarEventAfterWeekFilter;
import es.ubu.ubulexa.core.tools.filters.calendarevents.CalendarEventCoursesFilter;
import es.ubu.ubulexa.core.tools.filters.calendarevents.CalendarEventNextWeekFilter;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.MoodleTokenJwtClaimExtractor;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserIdFetcher;
import java.util.List;
import java.util.Set;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarEventResolver {

  private MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor;
  private MoodleUserIdFetcher moodleUserIdFetcher;
  private CalendarEventNextWeekFilter calendarEventNextWeekFilter;
  private CalendarEventAfterWeekFilter calendarEventAfterWeekFilter;
  private CalendarEventCoursesFilter calendarEventCoursesFilter;
  private CalendarJsonReader calendarJsonReader;
  private CourseResolver courseResolver;

  @PetiteInject
  public void setCalendarEventCoursesFilter(
      CalendarEventCoursesFilter calendarEventCoursesFilter) {
    this.calendarEventCoursesFilter = calendarEventCoursesFilter;
  }

  @PetiteInject
  public void setCourseResolver(CourseResolver courseResolver) {
    this.courseResolver = courseResolver;
  }

  @PetiteInject
  public void setMoodleUserIdFetcher(
      MoodleUserIdFetcher moodleUserIdFetcher) {
    this.moodleUserIdFetcher = moodleUserIdFetcher;
  }

  @PetiteInject
  public void setCalendarJsonReader(CalendarJsonReader calendarJsonReader) {
    this.calendarJsonReader = calendarJsonReader;
  }

  @PetiteInject
  public void setCalendarEventNextWeekFilter(
      CalendarEventNextWeekFilter calendarEventNextWeekFilter) {
    this.calendarEventNextWeekFilter = calendarEventNextWeekFilter;
  }

  @PetiteInject
  public void setCalendarEventAfterWeekFilter(
      CalendarEventAfterWeekFilter calendarEventAfterWeekFilter) {
    this.calendarEventAfterWeekFilter = calendarEventAfterWeekFilter;
  }

  @PetiteInject
  public void setMoodleTokenJwtClaimExtractor(
      MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor) {
    this.moodleTokenJwtClaimExtractor = moodleTokenJwtClaimExtractor;
  }

  public CalendarEventResolverResult resolve(HandlerInput handlerInput) {
    CalendarEventResolverResult result = new CalendarEventResolverResult();

    String moodleToken = moodleTokenJwtClaimExtractor.extract(handlerInput);

    String userId = moodleUserIdFetcher.fetch(moodleToken);
    if (StringUtil.isBlank(userId)) {
      return result;
    }

    Set<String> courses = courseResolver.resolve(moodleToken, userId);

    if (CollectionUtils.isEmpty(courses)) {
      return result;
    }

    List<CalendarEvent> events = calendarJsonReader.read();
    if (CollectionUtils.isEmpty(events)) {
      return result;
    }

    List<CalendarEvent> userEvents = calendarEventCoursesFilter.filter(events, courses);
    if (CollectionUtils.isEmpty(userEvents)) {
      return result;
    }

    List<CalendarEvent> nextWeekEvents = calendarEventNextWeekFilter.filter(userEvents);
    result.setNextWeekEvents(nextWeekEvents);

    List<CalendarEvent> afterNextWeekEvents = calendarEventAfterWeekFilter.filter(userEvents);
    result.setAfterNextWeekEvents(afterNextWeekEvents);

    return result;
  }
}
