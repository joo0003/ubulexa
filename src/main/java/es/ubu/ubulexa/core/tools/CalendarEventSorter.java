package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.util.ArrayList;
import java.util.List;
import jodd.petite.meta.PetiteBean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

@PetiteBean
public class CalendarEventSorter {

  public List<CalendarEvent> sort(List<CalendarEvent> events) {
    if (CollectionUtils.isEmpty(events)) {
      return new ArrayList<>();
    }

    MultiValuedMap<String, CalendarEvent> map = new ArrayListValuedHashMap<>();
    for (CalendarEvent event : events) {
      map.put(event.getEventDate(), event);
    }
    return new ArrayList<>(map.values());
  }
}
