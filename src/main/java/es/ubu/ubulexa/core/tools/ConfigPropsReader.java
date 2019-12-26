package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.utils.ClassLoaderUtils;
import es.ubu.ubulexa.core.utils.PropsUtils;
import java.io.InputStream;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;
import jodd.util.StringPool;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class ConfigPropsReader {

  private static Logger LOGGER = LoggerFactory.getLogger(ConfigPropsReader.class);

  private static final String CONFIG_PROPS_FILENAME = "config.props";

  private static final String BUCKET_NAME_KEY = "bucketName";
  private static final String THREEFISH_SECRET_KEY = "threefishSecret";
  private static final String MOODLE_HOST_URL_KEY = "moodleHostUrl";
  private static final String JWT_SECRET_KEY = "jwtSecret";
  private static final String SKILL_ID_KEY = "skillId";

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
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }
    return null;
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