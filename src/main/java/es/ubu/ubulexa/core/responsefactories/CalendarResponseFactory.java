package es.ubu.ubulexa.core.responsefactories;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.CalendarEvent;
import es.ubu.ubulexa.core.tools.CalendarEventResolver;
import es.ubu.ubulexa.core.tools.CalendarEventSorter;
import es.ubu.ubulexa.core.tools.speechbuilders.AnyDayEventTemplatedSpeechBuilder;
import es.ubu.ubulexa.core.tools.speechbuilders.TodayEventTemplatedSpeechBuilder;
import es.ubu.ubulexa.core.tools.speechbuilders.TomorrowEventTemplatedSpeechBuilder;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;
import jodd.util.ArraysUtil;
import jodd.util.StringPool;
import jodd.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;

@PetiteBean
public class CalendarResponseFactory extends AbstractResponseFactory {

  private CalendarEventResolver calendarEventResolver;
  private CalendarEventSorter calendarEventSorter;
  private TodayEventTemplatedSpeechBuilder todayEventTemplatedSpeechBuilder;
  private TomorrowEventTemplatedSpeechBuilder tomorrowEventTemplatedSpeechBuilder;
  private AnyDayEventTemplatedSpeechBuilder anyDayEventTemplatedSpeechBuilder;

  @PetiteInject
  public void setAnyDayEventTemplatedSpeechBuilder(
      AnyDayEventTemplatedSpeechBuilder anyDayEventTemplatedSpeechBuilder) {
    this.anyDayEventTemplatedSpeechBuilder = anyDayEventTemplatedSpeechBuilder;
  }

  @PetiteInject
  public void setTomorrowEventTemplatedSpeechBuilder(
      TomorrowEventTemplatedSpeechBuilder tomorrowEventTemplatedSpeechBuilder) {
    this.tomorrowEventTemplatedSpeechBuilder = tomorrowEventTemplatedSpeechBuilder;
  }

  @PetiteInject
  public void setTodayEventTemplatedSpeechBuilder(
      TodayEventTemplatedSpeechBuilder todayEventTemplatedSpeechBuilder) {
    this.todayEventTemplatedSpeechBuilder = todayEventTemplatedSpeechBuilder;
  }

  @PetiteInject
  public void setCalendarEventSorter(CalendarEventSorter calendarEventSorter) {
    this.calendarEventSorter = calendarEventSorter;
  }

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
    String speech = (CollectionUtils.isEmpty(events)) ?
        buildNoEventsSpeechText(dictionary) :
        buildYesEventsSpeechText(dictionary, events);

    speech += StringPool.SPACE;
    speech += dictionary.getValue(Constants.DISCLAIMER_SPEECH_TEXT_KEY);

    return speech;
  }

  private String buildNoEventsSpeechText(Props dictionary) {
    return dictionary.getValue(Constants.CALENDAR_NO_EVENTS_SPEECH_TEXT_KEY);
  }

  private String buildYesEventsSpeechText(Props dictionary, List<CalendarEvent> events) {
    List<CalendarEvent> sortedEvents = calendarEventSorter.sort(events);

    String[] speeches = new String[]{};

    speeches = ArraysUtil.append(
        speeches,
        dictionary.getValue(Constants.CALENDAR_EVENTS_INTRO_SPEECH_TEXT_KEY)
    );

    for (CalendarEvent event : sortedEvents) {
      speeches = ArraysUtil.append(speeches, buildSpeechText(dictionary, event));
    }

    return StringUtil.join(speeches, StringPool.SPACE);
  }

  private String buildSpeechText(Props dictionary, CalendarEvent event) {
    if (isTodayEvent(event)) {
      return todayEventTemplatedSpeechBuilder.build(dictionary, event);
    }
    if (isTomorrowEvent(event)) {
      return tomorrowEventTemplatedSpeechBuilder.build(dictionary, event);
    }
    return anyDayEventTemplatedSpeechBuilder.build(dictionary, event);
  }

  private LocalDate getToday() {
    Instant now = instantUtils().now();
    return localDateUtils().from(now, Constants.CET_ZONE_ID);
  }

  private boolean isTomorrowEvent(CalendarEvent event) {
    LocalDate eventLocalDate = localDateUtils().parse(event.getEventDate());
    LocalDate tomorrow = getToday().plusDays(1);
    return tomorrow.isEqual(eventLocalDate);
  }

  private boolean isTodayEvent(CalendarEvent event) {
    LocalDate eventLocalDate = localDateUtils().parse(event.getEventDate());
    LocalDate today = getToday();
    return today.isEqual(eventLocalDate);
  }
}
