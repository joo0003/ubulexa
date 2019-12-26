package es.ubu.ubulexa.skill;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import es.ubu.ubulexa.skill.config.petite.PetiteInitializer;
import es.ubu.ubulexa.skill.config.petite.PetitePostInitializer;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.PetiteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaHandler implements RequestStreamHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(LambdaHandler.class);

  private static PetiteContainer petiteContainer;

  public LambdaHandler() {
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
    LambdaHandlerModel lambdaHandlerModel = petiteContainer.getBean(LambdaHandlerModel.class);
    lambdaHandlerModel.handleRequest(inputStream, outputStream);
  }
}