package es.ubu.ubulexa.core.tools.speechbuilders;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import java.util.HashMap;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;
import jodd.util.StringPool;

@PetiteBean
public class TomorrowEventTemplatedSpeechBuilder extends AbstractTemplatedSpeechBuilder {

  public String build(Props dictionary, CalendarEvent event) {
    if (null == event) {
      return StringPool.EMPTY;
    }

    Map<String, String> params = new HashMap<>();
    params.put("0", event.getEventTitle());

    return parseTemplate(
        dictionary.getValue(Constants.CALENDAR_EVENTS_TOMORROW_SPEECH_TEXT_KEY),
        params
    );
  }
}
