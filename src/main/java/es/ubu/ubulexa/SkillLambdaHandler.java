package es.ubu.ubulexa;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import es.ubu.ubulexa.config.petite.PetiteInitializer;
import es.ubu.ubulexa.config.petite.PetitePostInitializer;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.PetiteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkillLambdaHandler implements RequestStreamHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(SkillLambdaHandler.class);

  private static PetiteContainer petiteContainer;

  public SkillLambdaHandler() {
    try {
      if (null == petiteContainer) {
        petiteContainer = PetiteInitializer.init();
        PetitePostInitializer.init(petiteContainer);
      }
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }

  @Override
  public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
    SkillLambdaHandlerModel skillLambdaHandlerModel = petiteContainer
        .getBean(SkillLambdaHandlerModel.class);
    skillLambdaHandlerModel.handleRequest(inputStream, outputStream);
  }
}