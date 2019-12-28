package es.ubu.ubulexa.core.responsefactories;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.responsefactories.calendar.NoCalendarEventsResponseFactory;
import es.ubu.ubulexa.core.tools.CalendarEventResolver;
import java.util.List;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarResponseFactory extends AbstractResponseFactory {

  private CalendarEventResolver calendarEventResolver;
  private NoCalendarEventsResponseFactory noCalendarEventsResponseFactory;

  @PetiteInject
  public void setNoCalendarEventsResponseFactory(
      NoCalendarEventsResponseFactory noCalendarEventsResponseFactory) {
    this.noCalendarEventsResponseFactory = noCalendarEventsResponseFactory;
  }

  @PetiteInject
  public void setCalendarEventResolver(
      CalendarEventResolver calendarEventResolver) {
    this.calendarEventResolver = calendarEventResolver;
  }

  @Override
  public Optional<Response> build(HandlerInput handlerInput) {

    List<CalendarEvent> events = calendarEventResolver.resolve(handlerInput);

    if (CollectionUtils.isEmpty(events)) {
      return noCalendarEventsResponseFactory.build(handlerInput);
    } else {
      return Optional.empty();
    }
  }
}
