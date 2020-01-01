package es.ubu.ubulexa.core.tools.filters.calendarevents;

import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.util.ArrayList;
import java.util.List;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarEventCourseFilter extends AbstractCalendarEventFilter {

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
    if (StringUtil.isBlank(event.getCourseId())) {
      return false;
    }
    return StringUtil.equals(appConfig().moodleCourseId(), event.getCourseId());
  }

  private boolean canFilter(List<CalendarEvent> events) {
    if (StringUtil.isBlank(appConfig().moodleCourseId())) {
      return false;
    }

    if (CollectionUtils.isEmpty(events)) {
      return false;
    }

    return true;
  }
}
