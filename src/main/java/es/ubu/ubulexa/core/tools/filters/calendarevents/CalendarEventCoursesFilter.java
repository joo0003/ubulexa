package es.ubu.ubulexa.core.tools.filters.calendarevents;

import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarEventCoursesFilter extends AbstractCalendarEventFilter {

  public List<CalendarEvent> filter(List<CalendarEvent> events, Set<String> courses) {
    List<CalendarEvent> filteredList = new ArrayList<>();

    if (canFilter(events)) {
      for (CalendarEvent event : events) {
        if (shouldBeIncluded(event, courses)) {
          filteredList.add(event);
        }
      }
    }

    return filteredList;
  }

  private boolean shouldBeIncluded(CalendarEvent event, Set<String> courses) {
    if (CollectionUtils.isEmpty(courses)) {
      return false;
    }

    String eventCourseId = StringUtil.trimDown(event.getCourseId());
    if (StringUtil.isBlank(eventCourseId)) {
      return false;
    }

    return courses.contains(eventCourseId);
  }

  private boolean canFilter(List<CalendarEvent> events) {
    return !CollectionUtils.isEmpty(events);
  }
}
