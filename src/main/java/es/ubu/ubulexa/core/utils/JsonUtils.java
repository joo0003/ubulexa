package es.ubu.ubulexa.core.utils;

import com.github.wnameless.json.flattener.JsonFlattener;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class JsonUtils {

  public JsonParser jsonParser() {
    return new JsonParser();
  }

  public JsonSerializer jsonSerializer() {
    return new JsonSerializer();
  }

  public String flatten(String str) {
    return JsonFlattener.flatten(str);
  }
}
