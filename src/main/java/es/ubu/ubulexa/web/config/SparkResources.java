package es.ubu.ubulexa.web.config;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.utils.FreemarkerUtils;
import es.ubu.ubulexa.core.controllers.HealthController;
import es.ubu.ubulexa.core.controllers.PrivacyPolicyController;
import es.ubu.ubulexa.core.controllers.WebAuthController;
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

  private FreemarkerUtils freemarkerUtils;
  private HealthController healthController;
  private WebAuthController webAuthController;
  private PrivacyPolicyController privacyPolicyController;

  @PetiteInject
  public void setPrivacyPolicyController(
      PrivacyPolicyController privacyPolicyController) {
    this.privacyPolicyController = privacyPolicyController;
  }

  @PetiteInject
  public void setFreemarkerUtils(FreemarkerUtils freemarkerUtils) {
    this.freemarkerUtils = freemarkerUtils;
  }

  @PetiteInject
  public void setWebAuthController(WebAuthController webAuthController) {
    this.webAuthController = webAuthController;
  }

  @PetiteInject
  public void setHealthController(HealthController healthController) {
    this.healthController = healthController;
  }

  public void defineResources() {
    defineExceptions();

    get("/health", (req, res) -> healthController.get(req, res));

    get("/webauth", (req, res) -> webAuthController.get(req, res), freemarkerUtils.engine());

    post("/webauth", (req, res) -> webAuthController.post(req, res), freemarkerUtils.engine());

    post("/webauth/token", (req, res) -> webAuthController.postToken(req, res));

    get("/privacypolicy", (req, res) -> privacyPolicyController.get(req, res),
        freemarkerUtils.engine());
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
