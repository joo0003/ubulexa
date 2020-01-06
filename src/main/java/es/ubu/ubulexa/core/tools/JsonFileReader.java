package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.utils.ClassLoaderUtils;
import es.ubu.ubulexa.core.utils.IOUtils;
import es.ubu.ubulexa.core.utils.JsonUtils;
import java.io.InputStream;
import jodd.json.JsonArray;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class JsonFileReader {

  private static Logger LOGGER = LoggerFactory.getLogger(JsonFileReader.class);

  private ClassLoaderUtils classLoaderUtils;
  private IOUtils ioUtils;
  private JsonUtils jsonUtils;

  @PetiteInject
  public void setIoUtils(IOUtils ioUtils) {
    this.ioUtils = ioUtils;
  }

  @PetiteInject
  public void setJsonUtils(JsonUtils jsonUtils) {
    this.jsonUtils = jsonUtils;
  }

  @PetiteInject
  public void setClassLoaderUtils(ClassLoaderUtils classLoaderUtils) {
    this.classLoaderUtils = classLoaderUtils;
  }

  public JsonArray read(String filename) {
    try {
      InputStream is = classLoaderUtils.getResourceAsStream(filename);
      String str = ioUtils.toString(is);
      return jsonUtils.jsonParser().parseAsJsonArray(str);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      return new JsonArray();
    }
  }
}
