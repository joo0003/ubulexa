package es.ubu.ubulexa.core.tools.filters.calendarevents;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jodd.petite.meta.PetiteBean;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarEventAfterWeekFilter extends AbstractCalendarEventFilter {

  public List<CalendarEvent> filter(List<CalendarEvent> events) {
    List<CalendarEvent> filteredList = new ArrayList<>();

    if (canFilter(events)) {

      Instant now = instantUtils().now();
      LocalDate today = localDateUtils().from(now, Constants.CET_ZONE_ID);
      LocalDate start = today.plusWeeks(1);

      for (CalendarEvent event : events) {
        if (shouldBeIncluded(event, start)) {
          filteredList.add(event);
        }
      }
    }

    return filteredList;
  }

  private boolean shouldBeIncluded(CalendarEvent event, LocalDate start) {
    LocalDate eventLocalDate = localDateUtils().parse(event.getEventDate());

    if (eventLocalDate.isBefore(start)) {
      return false;
    }

    LocalDate end = start.plusWeeks(1);

    return eventLocalDate.isBefore(end);
  }

  private boolean canFilter(List<CalendarEvent> events) {
    return !CollectionUtils.isEmpty(events);
  }
}
