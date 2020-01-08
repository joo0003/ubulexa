package es.ubu.ubulexa.core.tools.moodle;

import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import java.util.ArrayList;
import java.util.List;
import jodd.json.JsonArray;
import jodd.json.JsonObject;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringUtil;

@PetiteBean
public class MoodleUserCalendarEventsFetcher extends AbstractMoodleFetcher {

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
      ExceptionUtils.log(e);
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
