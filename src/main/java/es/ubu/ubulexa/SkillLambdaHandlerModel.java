package es.ubu.ubulexa;

import com.amazon.ask.Skill;
import com.amazon.ask.request.impl.BaseSkillRequest;
import com.amazon.ask.response.SkillResponse;
import es.ubu.ubulexa.config.SkillBuilder;
import es.ubu.ubulexa.tools.s3dumpers.RequestToS3Dumper;
import es.ubu.ubulexa.tools.s3dumpers.ResponseToS3Dumper;
import es.ubu.ubulexa.utils.IOUtils;
import es.ubu.ubulexa.utils.UuidUtils;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class SkillLambdaHandlerModel {

  private static final Logger LOGGER = LoggerFactory.getLogger(SkillLambdaHandlerModel.class);

  private UuidUtils uuidUtils;
  private SkillBuilder skillBuilder;
  private IOUtils ioUtils;
  private RequestToS3Dumper requestToS3Dumper;
  private ResponseToS3Dumper responseToS3Dumper;

  @PetiteInject
  public void setResponseToS3Dumper(
      ResponseToS3Dumper responseToS3Dumper) {
    this.responseToS3Dumper = responseToS3Dumper;
  }

  @PetiteInject
  public void setRequestToS3Dumper(
      RequestToS3Dumper requestToS3Dumper) {
    this.requestToS3Dumper = requestToS3Dumper;
  }

  @PetiteInject
  public void setUuidUtils(UuidUtils uuidUtils) {
    this.uuidUtils = uuidUtils;
  }

  @PetiteInject
  public void setSkillBuilder(SkillBuilder skillBuilder) {
    this.skillBuilder = skillBuilder;
  }

  @PetiteInject
  public void setIoUtils(IOUtils ioUtils) {
    this.ioUtils = ioUtils;
  }

  public void handleRequest(InputStream inputStream, OutputStream outputStream) {
    try {
      String uuid = uuidUtils.generate();

      byte[] inputBytes = ioUtils.toByteArray(inputStream);

      requestToS3Dumper.dump(uuid, inputBytes);

      Skill skill = skillBuilder.build();
      SkillResponse response = skill.execute(new BaseSkillRequest(inputBytes));

      if (null != response && response.isPresent()) {
        responseToS3Dumper.dump(uuid, response.getRawResponse());
        ioUtils.write(response.getRawResponse(), outputStream);
      }
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }
}