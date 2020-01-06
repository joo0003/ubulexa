package es.ubu.ubulexa.core.tools;

import static es.ubu.ubulexa.core.Constants.CALENDAR_JSON_FILENAME;

import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.util.ArrayList;
import java.util.List;
import jodd.json.JsonArray;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringPool;

@PetiteBean
public class CalendarJsonReader {

  private static List<CalendarEvent> EVENTS;

  private JsonFileReader jsonFileReader;

  @PetiteInject
  public void setJsonFileReader(JsonFileReader jsonFileReader) {
    this.jsonFileReader = jsonFileReader;
  }

  public List<CalendarEvent> read() {
    if (null == EVENTS) {
      EVENTS = readEvents();
    }
    return EVENTS;
  }

  private List<CalendarEvent> readEvents() {
    JsonArray jsonArray = jsonFileReader.read(CALENDAR_JSON_FILENAME);

    List<CalendarEvent> events = new ArrayList<>();
    if (!jsonArray.isEmpty()) {
      for (int i = 0; i < jsonArray.size(); i++) {
        String eventId = jsonArray.getJsonObject(i).getString("eventId", StringPool.EMPTY);
        String eventTitle = jsonArray.getJsonObject(i).getString("eventTitle", StringPool.EMPTY);
        String eventDate = jsonArray.getJsonObject(i).getString("eventDate", StringPool.EMPTY);

        CalendarEvent event = new CalendarEvent();
        event.setEventId(eventId);
        event.setEventDate(eventDate);
        event.setEventTitle(eventTitle);

        events.add(event);
      }
    }
    return events;
  }
}
