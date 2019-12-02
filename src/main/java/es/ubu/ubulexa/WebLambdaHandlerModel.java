package es.ubu.ubulexa;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import es.ubu.ubulexa.utils.StreamUtils;
import es.ubu.ubulexa.utils.UuidUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class WebLambdaHandlerModel {

  private static Logger LOGGER = LoggerFactory.getLogger(WebLambdaHandlerModel.class);

  // private S3Dumper s3Dumper;
  private UuidUtils uuidUtils;
  private StreamUtils streamUtils;

  @PetiteInject
  public void setStreamUtils(StreamUtils streamUtils) {
    this.streamUtils = streamUtils;
  }

  @PetiteInject
  public void setUuidUtils(UuidUtils uuidUtils) {
    this.uuidUtils = uuidUtils;
  }

  /*
  @PetiteInject
  public void setS3Dumper(S3Dumper s3Dumper) {
    this.s3Dumper = s3Dumper;
  }
   */

  public void handleRequest(
      SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler,
      InputStream inputStream,
      OutputStream outputStream,
      Context context
  ) {
    try {
      String uuid = uuidUtils.generate();

      //****

      byte[] inputBytes = streamUtils.readBytes(inputStream);
      //s3Dumper.dump(uuid, Constants.SUBFOLDER_WEB_REQUESTS_NAME, inputBytes);

      //****

      ByteArrayInputStream bais = new ByteArrayInputStream(inputBytes);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      handler.proxyStream(bais, baos, context);

      //****

      byte[] outputBytes = baos.toByteArray();
      //s3Dumper.dump(uuid, Constants.SUBFOLDER_WEB_RESPONSES_NAME, outputBytes);

      //****

      outputStream.write(outputBytes);
      outputStream.close();
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }
}
