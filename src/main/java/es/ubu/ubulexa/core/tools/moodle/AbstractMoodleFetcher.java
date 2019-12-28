package es.ubu.ubulexa.core.tools.moodle;

import es.ubu.ubulexa.core.http.MoodleHttpClient;
import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.JsonUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractMoodleFetcher {

  private MoodleHttpClient moodleHttpClient;
  private JsonUtils jsonUtils;
  private AppConfig appConfig;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setJsonUtils(JsonUtils jsonUtils) {
    this.jsonUtils = jsonUtils;
  }

  @PetiteInject
  public void setMoodleHttpClient(MoodleHttpClient moodleHttpClient) {
    this.moodleHttpClient = moodleHttpClient;
  }

  public MoodleHttpClient moodleHttpClient() {
    return moodleHttpClient;
  }

  public JsonUtils jsonUtils() {
    return jsonUtils;
  }

  public AppConfig appConfig() {
    return appConfig;
  }
}
