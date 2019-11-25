package es.ubu.ubulexa.http;

import es.ubu.ubulexa.tools.SystemEnvReader;
import es.ubu.ubulexa.utils.HttpRequestUtils;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class MoodleHttpClient {

  private static final String MOODLE_MOBILE_APP = "MOODLE_MOBILE_APP";

  private SystemEnvReader systemEnvReader;
  private HttpRequestUtils httpRequestUtils;

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  @PetiteInject
  public void setHttpRequestUtils(HttpRequestUtils httpRequestUtils) {
    this.httpRequestUtils = httpRequestUtils;
  }

  public String token(String username, String password) {
    String url = systemEnvReader.moodleHostUrl() + "/login/token.php";
    url += "?username=" + username;
    url += "&password=" + password;
    url += "&service=" + MOODLE_MOBILE_APP;

    HttpRequest httpRequest = httpRequestUtils.get(url);
    HttpResponse response = httpRequest.send();
    return response.body();
  }
}
