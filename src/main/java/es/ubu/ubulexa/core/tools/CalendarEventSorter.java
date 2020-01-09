package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import jodd.petite.meta.PetiteBean;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarEventSorter {

  public List<CalendarEvent> sort(List<CalendarEvent> events) {
    if (CollectionUtils.isEmpty(events)) {
      return new ArrayList<>();
    }

    Map<String, CalendarEvent> map = new TreeMap<>();
    for (CalendarEvent event : events) {
      map.put(event.getEventDate(), event);
    }
    return new ArrayList<>(map.values());
  }
}
