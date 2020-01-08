package es.ubu.ubulexa.skill;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import es.ubu.ubulexa.skill.config.petite.PetiteInitializer;
import es.ubu.ubulexa.skill.config.petite.PetitePostInitializer;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.petite.PetiteContainer;

public class LambdaHandler implements RequestStreamHandler {

  private static PetiteContainer petiteContainer;

  public LambdaHandler() {
    try {
      if (null == petiteContainer) {
        petiteContainer = PetiteInitializer.init();
        PetitePostInitializer.init(petiteContainer);
      }
    } catch (Exception e) {
      ExceptionUtils.log(e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
    LambdaHandlerModel lambdaHandlerModel = petiteContainer.getBean(LambdaHandlerModel.class);
    lambdaHandlerModel.handleRequest(inputStream, outputStream);
  }
}