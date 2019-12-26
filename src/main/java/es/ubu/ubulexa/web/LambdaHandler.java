package es.ubu.ubulexa.web;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import es.ubu.ubulexa.web.config.SparkResources;
import es.ubu.ubulexa.web.config.petite.PetiteInitializer;
import es.ubu.ubulexa.web.config.petite.PetitePostInitializer;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.PetiteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class LambdaHandler implements RequestStreamHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(LambdaHandler.class);

  private static SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
  private static PetiteContainer petiteContainer;

  public LambdaHandler() {
    try {
      if (null == petiteContainer) {
        petiteContainer = PetiteInitializer.init();
        PetitePostInitializer.init(petiteContainer);
      }

      if (null == handler) {
        handler = SparkLambdaContainerHandler.getAwsProxyHandler();
        petiteContainer.getBean(SparkResources.class).defineResources();
        Spark.awaitInitialization();
      }
    } catch (ContainerInitializationException e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
      throw new RuntimeException(e);
    }
  }

  @Override
  public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
    LambdaHandlerModel lambdaHandlerModel = petiteContainer.getBean(LambdaHandlerModel.class);
    lambdaHandlerModel.handleRequest(handler, inputStream, outputStream, context);
  }
}
