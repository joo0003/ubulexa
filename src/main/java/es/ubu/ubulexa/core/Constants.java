package es.ubu.ubulexa.core;

import java.time.ZoneId;
import java.util.TimeZone;

public class Constants {

  public static final String APPLICATION_JSON_MEDIA_TYPE = "application/json";
  public static final String APPLICATION_PLAIN_TEXT_MEDIA_TYPE = "text/plain";
  public static final String APPLICATION_HTML_MEDIA_TYPE = "text/html";

  public static final String CALENDAR_JSON_FILENAME = "calendar.json";
  public static final String CONFIG_PROPS_FILENAME = "config.props";

  public static final String BUCKET_NAME_KEY = "bucketName";
  public static final String THREEFISH_SECRET_KEY = "threefishSecret";
  public static final String MOODLE_HOST_URL_KEY = "moodleHostUrl";
  public static final String JWT_SECRET_KEY = "jwtSecret";
  public static final String SKILL_ID_KEY = "skillId";

  public static final String CHELO_COURSE_1_ID = "1685";
  public static final String CHELO_COURSE_2_ID = "11607";
  public static final String RAUL_COURSE_1_ID = "";

  public static final String FALLBACK_INTENT = "AMAZON.FallbackIntent";
  public static final String CANCEL_INTENT = "AMAZON.CancelIntent";
  public static final String STOP_INTENT = "AMAZON.StopIntent";
  public static final String HELP_INTENT = "AMAZON.HelpIntent";
  public static final String NAVIGATE_HOME_INTENT = "AMAZON.NavigateHomeIntent";
  public static final String CALENDAR_INTENT = "CalendarIntent";

  public static final String CET_TIMEZONE_ID = "CET";
  public static final ZoneId CET_ZONE_ID = TimeZone.getTimeZone(CET_TIMEZONE_ID).toZoneId();

  public static final String ES_ES_LOCALE_CODE = "es-ES";
  public static final String EN_US_LOCALE_CODE = "en-US";

  public static final String VOICE_CONCHITA = "Conchita";
  public static final String VOICE_ENRIQUE = "Enrique";

  public static final String EN_PROPS_FILENAME = "i18n/en.props";
  public static final String ES_PROPS_FILENAME = "i18n/es.props";

  public static final String JWT_USER_ID_CLAIM = "userId";
  public static final String JWT_USERNAME_CLAIM = "username";
  public static final String JWT_MOODLE_TOKEN_CLAIM = "moodleToken";

  public static final String SUBFOLDER_RAW_REQUESTS_NAME = "raw_requests";
  public static final String SUBFOLDER_RAW_RESPONSES_NAME = "raw_responses";
  public static final String SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME = "first_time_user_events";
  public static final String SUBFOLDER_WEB_REQUESTS_NAME = "web_requests";
  public static final String SUBFOLDER_WEB_RESPONSES_NAME = "web_responses";
  public static final String SUBFOLDER_TOKEN_EXCHANGE_NAME = "token_exchange";

  public static final String FAKE_AMAZON_UBU_EMAIL = "amazon123@alu.ubu.es";

  public static final String GOODBYE_SPEECH_TEXT_KEY = "speechtext.goodbye";
  public static final String HELP_SPEECH_TEXT_KEY = "speechtext.help";
  public static final String FALLBACK_SPEECH_TEXT_KEY = "speechtext.fallback";
  public static final String FIRST_TIME_SPEECH_TEXT_KEY = "speechtext.firsttime";
  public static final String DISCLAIMER_SPEECH_TEXT_KEY = "speechtext.disclaimer";
  public static final String MOODLE_TOKEN_INVALID_SPEECH_TEXT_KEY = "speechtext.moodletokeninvalid";
  public static final String CALENDAR_NO_EVENTS_SPEECH_TEXT_KEY = "speechtext.calendar.noevents";
  public static final String CALENDAR_EVENTS_INTRO_SPEECH_TEXT_KEY = "speechtext.calendar.events.intro";
  public static final String CALENDAR_EVENTS_TODAY_SPEECH_TEXT_KEY = "speechtext.calendar.events.today";
  public static final String CALENDAR_EVENTS_TOMORROW_SPEECH_TEXT_KEY = "speechtext.calendar.events.tomorrow";
  public static final String CALENDAR_EVENTS_ANY_DAY_SPEECH_TEXT_KEY = "speechtext.calendar.events.anyday";
  public static final String CALENDAR_EVENTS_LAST_DAY_SPEECH_TEXT_KEY = "speechtext.calendar.events.lastday";
  public static final String CALENDAR_EVENTS_ANY_DAY_WITH_DATE_SPEECH_TEXT_KEY = "speechtext.calendar.events.anydaywithdate";

  public static final String PREFIX_DAYS_SPEECH_TEXT_KEY = "speechtext.days.";
  public static final String PREFIX_MONTHS_SPEECH_TEXT_KEY = "speechtext.months.";

  public static final String FIRST_TIME_SESSION_ATTR_KEY = "firstTime";
  public static final String MOODLE_TOKEN_INVALID_SESSION_ATTR_KEY = "moodleTokenInvalid";
}
