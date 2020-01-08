package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.utils.ClassLoaderUtils;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import java.io.InputStream;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class DictionaryFileResolver {

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
      ExceptionUtils.log(e);
    }
    return null;
  }
}
