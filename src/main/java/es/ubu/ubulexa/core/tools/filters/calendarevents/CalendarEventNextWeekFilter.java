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
public class CalendarEventNextWeekFilter extends AbstractCalendarEventFilter {

  public List<CalendarEvent> filter(List<CalendarEvent> events) {
    List<CalendarEvent> filteredList = new ArrayList<>();

    if (canFilter(events)) {
      for (CalendarEvent event : events) {
        if (shouldBeIncluded(event)) {
          filteredList.add(event);
        }
      }
    }

    return filteredList;
  }

  private boolean shouldBeIncluded(CalendarEvent event) {
    Instant now = instantUtils().now();

    LocalDate today = localDateUtils().from(now, Constants.CET_ZONE_ID);

    //Instant eventStartInstant = instantUtils().fromSecs(event.getTimestart());

    LocalDate eventLocalDate = localDateUtils().parse(event.getEventDate());

    return !eventLocalDate.isBefore(today);
  }

  private boolean canFilter(List<CalendarEvent> events) {
    return !CollectionUtils.isEmpty(events);
  }
}
