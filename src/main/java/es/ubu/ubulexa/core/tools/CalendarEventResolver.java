package es.ubu.ubulexa.core.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.tools.filters.calendarevents.CalendarEventCourseFilter;
import es.ubu.ubulexa.core.tools.filters.calendarevents.CalendarEventNextWeekFilter;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.MoodleTokenJwtClaimExtractor;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserCalendarEventsFetcher;
import java.util.ArrayList;
import java.util.List;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarEventResolver {

  private MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor;
  private MoodleUserCalendarEventsFetcher moodleUserCalendarEventsFetcher;
  private CalendarEventCourseFilter calendarEventCourseFilter;
  private CalendarEventNextWeekFilter calendarEventNextWeekFilter;

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

    List<CalendarEvent> events = moodleUserCalendarEventsFetcher.fetch(moodleToken);
    if (CollectionUtils.isEmpty(events)) {
      return new ArrayList<>();
    }

    events = calendarEventCourseFilter.filter(events);
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
