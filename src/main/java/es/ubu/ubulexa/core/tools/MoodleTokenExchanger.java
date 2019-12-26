package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.http.MoodleHttpClient;
import es.ubu.ubulexa.core.utils.Base64Utils;
import es.ubu.ubulexa.core.utils.JsonUtils;
import java.util.Map;
import jodd.json.JsonParser;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

@PetiteBean
public class MoodleTokenExchanger {

  private MoodleHttpClient moodleHttpClient;
  private JsonUtils jsonUtils;
  private Base64Utils base64Utils;

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

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

    if (StringUtil.equals(Constants.FAKE_AMAZON_UBU_EMAIL, username)) {
      return base64Utils.encode(Constants.FAKE_AMAZON_UBU_EMAIL);
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