package es.ubu.ubulexa.core.tools.speechbuilders;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;
import jodd.util.StringPool;

@PetiteBean
public class AnyDayEventWithDateTemplatedSpeechBuilder extends AbstractTemplatedSpeechBuilder {

  public String build(Props dictionary, CalendarEvent event) {
    if (null == event) {
      return StringPool.EMPTY;
    }

    LocalDate localDate = localDateUtils().parse(event.getEventDate());

    String dayName = dictionary
        .getValue(Constants.PREFIX_DAYS_SPEECH_TEXT_KEY + localDate.getDayOfWeek().getValue());

    String monthName = dictionary
        .getValue(Constants.PREFIX_MONTHS_SPEECH_TEXT_KEY + localDate.getMonth().getValue());

    Map<String, String> params = new HashMap<>();
    params.put("0", dayName);
    params.put("1", localDate.getDayOfMonth() + " de " + monthName);
    params.put("2", event.getEventTitle());

    return parseTemplate(
        dictionary.getValue(Constants.CALENDAR_EVENTS_ANY_DAY_WITH_DATE_SPEECH_TEXT_KEY),
        params
    );
  }
}
