package es.ubu.ubulexa.tools;

import es.ubu.ubulexa.http.MoodleHttpClient;
import es.ubu.ubulexa.utils.JsonUtils;
import java.util.Map;
import jodd.json.JsonParser;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

@PetiteBean
public class MoodleTokenExchanger {

  private MoodleHttpClient moodleHttpClient;
  private JsonUtils jsonUtils;

  @PetiteInject
  public void setJsonUtils(JsonUtils jsonUtils) {
    this.jsonUtils = jsonUtils;
  }

  @PetiteInject
  public void setMoodleHttpClient(MoodleHttpClient moodleHttpClient) {
    this.moodleHttpClient = moodleHttpClient;
  }

  public String exchange(String username, String password) {
    if (StringUtils.isBlank(username)) {
      return null;
    }

    if (StringUtils.isBlank(password)) {
      return null;
    }

    String response = moodleHttpClient.token(username, password);

    if (StringUtils.isBlank(response)) {
      return null;
    }

    JsonParser jsonParser = jsonUtils.jsonParser();

    Map<String, Object> map = jsonParser.parse(response);
    if (MapUtils.isEmpty(map)) {
      return null;
    }

    return map.containsKey("token") ? map.get("token").toString() : null;
  }
}