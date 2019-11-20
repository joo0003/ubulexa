package es.ubu.ubulexa.tools;

import static spark.Spark.exception;
import static spark.Spark.get;

import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.controllers.HealthController;
import jodd.exception.ExceptionUtil;
import jodd.net.HttpStatus;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class SparkResources {

  private static Logger LOGGER = LoggerFactory.getLogger(SparkResources.class);

  private HealthController healthController;

  @PetiteInject
  public void setHealthController(HealthController healthController) {
    this.healthController = healthController;
  }

  public void defineResources() {
    defineExceptions();

    get("/health", (req, res) -> healthController.get(req, res));
  }

  private void defineExceptions() {
    exception(Exception.class, (exception, request, response) -> {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(exception));
      response.status(HttpStatus.error500().status());
      response.type(Constants.APPLICATION_JSON_MEDIA_TYPE);
      response.body(StringPool.EMPTY);
    });
  }
}
