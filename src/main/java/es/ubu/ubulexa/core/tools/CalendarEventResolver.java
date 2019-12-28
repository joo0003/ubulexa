package es.ubu.ubulexa.core.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.MoodleTokenJwtClaimExtractor;
import es.ubu.ubulexa.core.tools.moodle.MoodleUserCalendarEventsFetcher;
import java.util.List;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class CalendarEventResolver {

  private MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor;
  private MoodleUserCalendarEventsFetcher moodleUserCalendarEventsFetcher;

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

    return events;
  }
}
