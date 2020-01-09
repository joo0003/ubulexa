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
public class AnyDayEventTemplatedSpeechBuilder extends AbstractTemplatedSpeechBuilder {

  public String build(Props dictionary, CalendarEvent event) {
    if (null == event) {
      return StringPool.EMPTY;
    }

    LocalDate localDate = localDateUtils().parse(event.getEventDate());

    String dayName = dictionary
        .getValue(Constants.PREFIX_DAYS_SPEECH_TEXT_KEY + localDate.getDayOfWeek().getValue());

    Map<String, String> params = new HashMap<>();
    params.put("0", dayName);
    params.put("1", event.getEventTitle());

    return parseTemplate(
        dictionary.getValue(Constants.CALENDAR_EVENTS_ANY_DAY_SPEECH_TEXT_KEY),
        params
    );
  }
}
