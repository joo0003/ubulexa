package es.ubu.ubulexa.core.tools.moodle;

import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.util.ArrayList;
import java.util.List;
import jodd.json.JsonArray;
import jodd.json.JsonObject;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class MoodleUserCalendarEventsFetcher extends AbstractMoodleFetcher {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MoodleUserCalendarEventsFetcher.class);

  public List<CalendarEvent> fetch(String moodleToken) {
    List<CalendarEvent> events = new ArrayList<>();

    try {
      String response = moodleHttpClient().getUserCalendarEvents(moodleToken);

      JsonObject jsonObject = jsonUtils().jsonParser().parseAsJsonObject(response);

      JsonArray jsonArray = jsonObject.getJsonArray("events", new JsonArray());

      if (!jsonArray.isEmpty()) {
        for (int i = 0; i < jsonArray.size(); i++) {
          CalendarEvent event = buildEvent(jsonArray.getJsonObject(i));
          events.add(event);
        }
      }
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }

    return events;
  }

  private CalendarEvent buildEvent(JsonObject jsonObject) {
    CalendarEvent event = new CalendarEvent();
    event.setEventId(StringUtil.toString(jsonObject.getInteger("id", null)));
    event.setCourseId(StringUtil.toString(jsonObject.getInteger("courseid", null)));
    event.setName(jsonObject.getString("name", null));
    event.setDescription(jsonObject.getString("description", null));
    event.setTimestart(jsonObject.getInteger("timestart", null));
    return event;
  }
}
