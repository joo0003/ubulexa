package es.ubu.ubulexa.core.utils;

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
}
