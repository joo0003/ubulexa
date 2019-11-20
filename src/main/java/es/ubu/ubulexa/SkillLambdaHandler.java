package es.ubu.ubulexa;

import com.amazonaws.services.lambda.runtime.Context;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkillLambdaHandler extends AbstractLambdaHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(SkillLambdaHandler.class);

  public SkillLambdaHandler() {
    try {
      initPetiteContainer();
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