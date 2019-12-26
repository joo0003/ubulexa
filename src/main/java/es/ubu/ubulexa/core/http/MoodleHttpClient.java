package es.ubu.ubulexa.core.http;

import static es.ubu.ubulexa.core.Constants.MOODLE_MOBILE_APP;

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
    url += "&service=" + MOODLE_MOBILE_APP;

    HttpRequest httpRequest = httpRequestUtils.get(url);
    HttpResponse response = httpRequest.send();
    return response.body();
  }
}
