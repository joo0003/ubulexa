package es.ubu.ubulexa.skill;

import com.amazon.ask.Skill;
import com.amazon.ask.request.impl.BaseSkillRequest;
import com.amazon.ask.response.SkillResponse;
import es.ubu.ubulexa.core.tools.s3dumpers.SkillRequestS3Dumper;
import es.ubu.ubulexa.core.tools.s3dumpers.SkillResponseS3Dumper;
import es.ubu.ubulexa.core.utils.IOUtils;
import es.ubu.ubulexa.core.utils.UuidUtils;
import es.ubu.ubulexa.skill.config.SkillBuilder;
import java.io.InputStream;
import java.io.OutputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class LambdaHandlerModel {

  private static final Logger LOGGER = LoggerFactory.getLogger(LambdaHandlerModel.class);

  private UuidUtils uuidUtils;
  private SkillBuilder skillBuilder;
  private IOUtils ioUtils;
  private SkillRequestS3Dumper skillRequestS3Dumper;
  private SkillResponseS3Dumper skillResponseS3Dumper;

  @PetiteInject
  public void setSkillResponseS3Dumper(
      SkillResponseS3Dumper skillResponseS3Dumper) {
    this.skillResponseS3Dumper = skillResponseS3Dumper;
  }

  @PetiteInject
  public void setSkillRequestS3Dumper(
      SkillRequestS3Dumper skillRequestS3Dumper) {
    this.skillRequestS3Dumper = skillRequestS3Dumper;
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

      skillRequestS3Dumper.dump(uuid, inputBytes);

      Skill skill = skillBuilder.build();
      SkillResponse response = skill.execute(new BaseSkillRequest(inputBytes));

      if (null != response && response.isPresent()) {
        skillResponseS3Dumper.dump(uuid, response.getRawResponse());
        ioUtils.write(response.getRawResponse(), outputStream);
      }
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }
}