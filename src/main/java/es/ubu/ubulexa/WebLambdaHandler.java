package es.ubu.ubulexa;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import es.ubu.ubulexa.tools.SparkResources;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class WebLambdaHandler extends AbstractLambdaHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(WebLambdaHandler.class);

  private static SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

  public WebLambdaHandler() {
    try {
      initPetiteContainer();

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
    WebLambdaHandlerModel lambdaHandlerModel = petiteContainer.getBean(WebLambdaHandlerModel.class);
    lambdaHandlerModel.handleRequest(handler, inputStream, outputStream, context);
  }
}
