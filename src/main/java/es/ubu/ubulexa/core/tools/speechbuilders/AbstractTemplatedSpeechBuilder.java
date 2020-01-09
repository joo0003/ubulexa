package es.ubu.ubulexa.core.tools.speechbuilders;

import es.ubu.ubulexa.core.utils.TemplateParserUtils;
import es.ubu.ubulexa.core.utils.time.LocalDateUtils;
import java.util.Map;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractTemplatedSpeechBuilder {

  private TemplateParserUtils templateParserUtils;
  private LocalDateUtils localDateUtils;

  @PetiteInject
  public void setLocalDateUtils(LocalDateUtils localDateUtils) {
    this.localDateUtils = localDateUtils;
  }

  @PetiteInject
  public void setTemplateParserUtils(TemplateParserUtils templateParserUtils) {
    this.templateParserUtils = templateParserUtils;
  }

  protected LocalDateUtils localDateUtils() {
    return localDateUtils;
  }

  protected String parseTemplate(String str, Map<String, String> params) {
    return templateParserUtils.parse(str, params);
  }
}
