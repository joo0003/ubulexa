package es.ubu.ubulexa.core;

public class Constants {

  public static final String APPLICATION_JSON_MEDIA_TYPE = "application/json";
  public static final String APPLICATION_PLAIN_TEXT_MEDIA_TYPE = "text/plain";
  public static final String APPLICATION_HTML_MEDIA_TYPE = "text/html";

  public static final String MOODLE_MOBILE_APP = "MOODLE_MOBILE_APP";

  public static final String FALLBACK_INTENT = "AMAZON.FallbackIntent";
  public static final String CANCEL_INTENT = "AMAZON.CancelIntent";
  public static final String STOP_INTENT = "AMAZON.StopIntent";
  public static final String HELP_INTENT = "AMAZON.HelpIntent";
  public static final String NAVIGATE_HOME_INTENT = "AMAZON.NavigateHomeIntent";
  public static final String VOICE_CHANGE_INTENT = "VoiceChangeIntent";
  public static final String CALENDAR_INTENT = "CalendarIntent";

  public static final String ES_ES_LOCALE_CODE = "es-ES";
  public static final String EN_US_LOCALE_CODE = "en-US";

  public static final String VOICE_CONCHITA = "Conchita";
  public static final String VOICE_ENRIQUE = "Enrique";

  public static final String EN_PROPS_FILENAME = "i18n/en.props";
  public static final String ES_PROPS_FILENAME = "i18n/es.props";

  public static final String JWT_USERNAME_CLAIM = "username";
  public static final String JWT_MOODLE_TOKEN_CLAIM = "moodleToken";

  public static final String SUBFOLDER_RAW_REQUESTS_NAME = "raw_requests";
  public static final String SUBFOLDER_RAW_RESPONSES_NAME = "raw_responses";
  public static final String SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME = "first_time_user_events";
  public static final String SUBFOLDER_WEB_REQUESTS_NAME = "web_requests";
  public static final String SUBFOLDER_WEB_RESPONSES_NAME = "web_responses";

  public static final String FAKE_AMAZON_UBU_EMAIL = "amazon123@alu.ubu.es";

  public static final String NO_FIRST_TIME_USER_EVENT_FOUND_EXCEPTION_SPEECH_TEXT_KEY = "NoFirstTimeUserEventFoundException.speechtext";

  public static final String LAUNCH_REQUEST_SPEECH_TEXT_KEY = "LaunchRequest.speechtext";

  public static final String CANCEL_INTENT_SPEECH_TEXT_KEY = "CancelIntent.speechtext";
  public static final String STOP_INTENT_SPEECH_TEXT_KEY = "StopIntent.speechtext";
  public static final String VOICE_CHANGE_INTENT_SPEECH_TEXT_KEY = "VoiceChangeIntent.speechtext";
  public static final String FALLBACK_INTENT_SPEECH_TEXT_KEY = "FallbackIntent.speechtext";
  public static final String HELP_INTENT_SPEECH_TEXT_KEY = "HelpIntent.speechtext";
  public static final String CALENDAR_INTENT_SPEECH_TEXT_KEY = "CalendarIntent.speechtext";
}
