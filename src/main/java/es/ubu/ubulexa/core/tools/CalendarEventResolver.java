package es.ubu.ubulexa.core.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.tools.filters.calendarevents.CalendarEventCourseFilter;
import es.ubu.ubulexa.core.tools.filters.calendarevents.CalendarEventNextWeekFilter;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.MoodleTokenJwtClaimExtractor;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserCalendarEventsFetcher;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserCoursesFetcher;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserIdFetcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarEventResolver {

  private MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor;
  private MoodleUserCalendarEventsFetcher moodleUserCalendarEventsFetcher;
  private MoodleUserIdFetcher moodleUserIdFetcher;
  private MoodleUserCoursesFetcher moodleUserCoursesFetcher;
  private CalendarEventCourseFilter calendarEventCourseFilter;
  private CalendarEventNextWeekFilter calendarEventNextWeekFilter;
  private CalendarJsonReader calendarJsonReader;
  private AppConfig appConfig;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setMoodleUserIdFetcher(
      MoodleUserIdFetcher moodleUserIdFetcher) {
    this.moodleUserIdFetcher = moodleUserIdFetcher;
  }

  @PetiteInject
  public void setMoodleUserCoursesFetcher(
      MoodleUserCoursesFetcher moodleUserCoursesFetcher) {
    this.moodleUserCoursesFetcher = moodleUserCoursesFetcher;
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
  public void setCalendarEventCourseFilter(
      CalendarEventCourseFilter calendarEventCourseFilter) {
    this.calendarEventCourseFilter = calendarEventCourseFilter;
  }

  @PetiteInject
  public void setMoodleUserCalendarEventsFetcher(
      MoodleUserCalendarEventsFetcher moodleUserCalendarEventsFetcher) {
    this.moodleUserCalendarEventsFetcher = moodleUserCalendarEventsFetcher;
  }

  @PetiteInject
  public void setMoodleTokenJwtClaimExtractor(
      MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor) {
    this.moodleTokenJwtClaimExtractor = moodleTokenJwtClaimExtractor;
  }

  public List<CalendarEvent> resolve(HandlerInput handlerInput) {
    String moodleToken = moodleTokenJwtClaimExtractor.extract(handlerInput);

    String userId = moodleUserIdFetcher.fetch(moodleToken);
    if (StringUtil.isBlank(userId)) {
      return new ArrayList<>();
    }

    Set<String> courses = moodleUserCoursesFetcher.fetch(moodleToken, userId);

    if (CollectionUtils.isEmpty(courses)) {
      return new ArrayList<>();
    }

    if (!courses.contains(appConfig.moodleCourseId())) {
      return new ArrayList<>();
    }

    //List<CalendarEvent> events = moodleUserCalendarEventsFetcher.fetch(moodleToken);
    List<CalendarEvent> events = calendarJsonReader.read();
    if (CollectionUtils.isEmpty(events)) {
      return new ArrayList<>();
    }

    //events = calendarEventCourseFilter.filter(events);
    if (CollectionUtils.isEmpty(events)) {
      return new ArrayList<>();
    }

    events = calendarEventNextWeekFilter.filter(events);
    if (CollectionUtils.isEmpty(events)) {
      return new ArrayList<>();
    }

    return events;
  }
}
