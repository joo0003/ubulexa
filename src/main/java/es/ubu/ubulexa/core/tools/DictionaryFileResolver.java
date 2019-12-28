package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.matchers.intents.AbstractIntentMatcher;
import es.ubu.ubulexa.core.utils.ClassLoaderUtils;
import java.io.InputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class DictionaryFileResolver {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractIntentMatcher.class);

  private ClassLoaderUtils classLoaderUtils;

  @PetiteInject
  public void setClassLoaderUtils(ClassLoaderUtils classLoaderUtils) {
    this.classLoaderUtils = classLoaderUtils;
  }

  public InputStream resolve(String locale) {
    try {
      if (Constants.ES_ES_LOCALE_CODE.equals(locale)) {
        return classLoaderUtils.getResourceAsStream(Constants.ES_PROPS_FILENAME);
      }
      return classLoaderUtils.getResourceAsStream(Constants.EN_PROPS_FILENAME);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
    return null;
  }
}
