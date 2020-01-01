package es.ubu.ubulexa.core.responsefactories;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.tools.CalendarEventResolver;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import java.util.List;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarResponseFactory extends AbstractResponseFactory {

  private CalendarEventResolver calendarEventResolver;

  @PetiteInject
  public void setCalendarEventResolver(
      CalendarEventResolver calendarEventResolver) {
    this.calendarEventResolver = calendarEventResolver;
  }

  @Override
  public Optional<Response> build(HandlerInput handlerInput) {

    List<CalendarEvent> events = calendarEventResolver.resolve(handlerInput);

    String speechText = buildSpeechText(handlerInput, events);
    speechText = transformSpeechText(handlerInput, speechText);

    ResponseBuilder builder = responseBuilder(handlerInput);
    builder.withSpeech(speechText);
    return builder.build();
  }

  private String transformSpeechText(
      HandlerInput handlerInput,
      String speechText
  ) {
    VoiceTransformer voiceTransformer = resolveVoiceTransformer(handlerInput);
    return voiceTransformer.transform(speechText);
  }

  private String buildSpeechText(
      HandlerInput handlerInput,
      List<CalendarEvent> events
  ) {
    Props dictionary = resolveDictionary(handlerInput);
    return buildSpeechText(dictionary, events);
  }

  private String buildSpeechText(Props dictionary, List<CalendarEvent> events) {
    if (CollectionUtils.isEmpty(events)) {
      return dictionary.getValue(Constants.CALENDAR_NO_EVENTS_SPEECH_TEXT_KEY);
    }
    return dictionary.getValue(Constants.CALENDAR_NO_EVENTS_SPEECH_TEXT_KEY);
  }
}
