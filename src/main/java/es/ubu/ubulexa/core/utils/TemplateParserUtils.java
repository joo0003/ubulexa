package es.ubu.ubulexa.core.utils;

import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.template.MapTemplateParser;

@PetiteBean
public class TemplateParserUtils {

  public String parse(String str, Map<String, String> params) {
    return new MapTemplateParser().parseWithMap(str, params);
  }
}
