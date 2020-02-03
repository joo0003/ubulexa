package es.ubu.ubulexa.core.tools;

import static es.ubu.ubulexa.core.Constants.BUCKET_NAME_KEY;
import static es.ubu.ubulexa.core.Constants.CONFIG_PROPS_FILENAME;
import static es.ubu.ubulexa.core.Constants.JWT_SECRET_KEY;
import static es.ubu.ubulexa.core.Constants.MOODLE_HOST_URL_KEY;
import static es.ubu.ubulexa.core.Constants.SKILL_ID_KEY;
import static es.ubu.ubulexa.core.Constants.THREEFISH_SECRET_KEY;

import es.ubu.ubulexa.core.utils.ClassLoaderUtils;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import es.ubu.ubulexa.core.utils.PropsUtils;
import java.io.InputStream;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;
import jodd.util.StringPool;

@PetiteBean
public class ConfigPropsReader {

  private static Props PROPS;

  private ClassLoaderUtils classLoaderUtils;
  private PropsUtils propsUtils;

  @PetiteInject
  public void setPropsUtils(PropsUtils propsUtils) {
    this.propsUtils = propsUtils;
  }

  @PetiteInject
  public void setClassLoaderUtils(ClassLoaderUtils classLoaderUtils) {
    this.classLoaderUtils = classLoaderUtils;
  }

  private Props read() {
    try {
      if (null == PROPS) {
        InputStream is = classLoaderUtils.getResourceAsStream(CONFIG_PROPS_FILENAME);
        PROPS = propsUtils.load(is);
      }
      return PROPS;
    } catch (Exception e) {
      ExceptionUtils.log(e);
      return null;
    }
  }

  public String bucketName() {
    return read().getValueOrDefault(BUCKET_NAME_KEY, StringPool.EMPTY);
  }

  public String threefishSecret() {
    return read().getValueOrDefault(THREEFISH_SECRET_KEY, StringPool.EMPTY);
  }

  public String moodleHostUrl() {
    return read().getValueOrDefault(MOODLE_HOST_URL_KEY, StringPool.EMPTY);
  }

  public String jwtSecret() {
    return read().getValueOrDefault(JWT_SECRET_KEY, StringPool.EMPTY);
  }

  public String skillId() {
    return read().getValueOrDefault(SKILL_ID_KEY, StringPool.EMPTY);
  }
}