package es.ubu.ubulexa.core.pojos;

import java.util.List;

public class CalendarEventResolverResult {

  private List<CalendarEvent> nextWeekEvents;
  private List<CalendarEvent> afterNextWeekEvents;

  public List<CalendarEvent> getNextWeekEvents() {
    return nextWeekEvents;
  }

  public void setNextWeekEvents(List<CalendarEvent> nextWeekEvents) {
    this.nextWeekEvents = nextWeekEvents;
  }

  public List<CalendarEvent> getAfterNextWeekEvents() {
    return afterNextWeekEvents;
  }

  public void setAfterNextWeekEvents(
      List<CalendarEvent> afterNextWeekEvents) {
    this.afterNextWeekEvents = afterNextWeekEvents;
  }
}
