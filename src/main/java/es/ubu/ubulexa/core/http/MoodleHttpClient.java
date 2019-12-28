package es.ubu.ubulexa.core.http;

import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.HttpRequestUtils;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class MoodleHttpClient {

  private AppConfig appConfig;
  private HttpRequestUtils httpRequestUtils;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setHttpRequestUtils(HttpRequestUtils httpRequestUtils) {
    this.httpRequestUtils = httpRequestUtils;
  }

  public String token(String username, String password) {
    String url = appConfig.moodleHostUrl() + "/login/token.php";
    url += "?username=" + username;
    url += "&password=" + password;
    url += "&service=MOODLE_MOBILE_APP";

    HttpRequest httpRequest = httpRequestUtils.get(url);
    HttpResponse response = httpRequest.send();
    return response.body();
  }

  public String getSiteInfo(String moodleToken) {
    String url = appConfig.moodleHostUrl() + "/webservice/rest/server.php";
    url += "?moodlewsrestformat=json";
    url += "&wstoken=" + moodleToken;
    url += "&wsfunction=core_webservice_get_site_info";

    HttpRequest httpRequest = httpRequestUtils.get(url);
    HttpResponse response = httpRequest.send();
    return response.body();
  }

  public String getUserCourses(String moodleToken, String userId) {
    String url = appConfig.moodleHostUrl() + "/webservice/rest/server.php";
    url += "?moodlewsrestformat=json";
    url += "&wstoken=" + moodleToken;
    url += "&wsfunction=core_enrol_get_users_courses";
    url += "&userid=" + userId;

    HttpRequest httpRequest = httpRequestUtils.get(url);
    HttpResponse response = httpRequest.send();
    return response.body();
  }

  public String getUserCalendarEvents(String moodleToken) {
    String url = appConfig.moodleHostUrl() + "/webservice/rest/server.php";
    url += "?moodlewsrestformat=json";
    url += "&wstoken=" + moodleToken;
    url += "&wsfunction=core_calendar_get_calendar_events";

    HttpRequest httpRequest = httpRequestUtils.get(url);
    HttpResponse response = httpRequest.send();
    return response.body();
  }
}
