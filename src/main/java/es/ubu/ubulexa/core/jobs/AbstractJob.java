package es.ubu.ubulexa.core.jobs;

import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.tools.AccessTokenParser;
import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.tools.ThreefishDecrypter;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import es.ubu.ubulexa.core.utils.Base64Utils;
import es.ubu.ubulexa.core.utils.JsonUtils;
import java.util.List;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractJob implements Job {

  protected AppConfig appConfig;
  protected AmazonS3Utils amazonS3Utils;
  protected Base64Utils base64Utils;
  protected JsonUtils jsonUtils;

  private ThreefishDecrypter threefishDecrypter;
  private AccessTokenParser accessTokenParser;

  @PetiteInject
  public void setAccessTokenParser(AccessTokenParser accessTokenParser) {
    this.accessTokenParser = accessTokenParser;
  }

  @PetiteInject
  public void setThreefishDecrypter(ThreefishDecrypter threefishDecrypter) {
    this.threefishDecrypter = threefishDecrypter;
  }

  @PetiteInject
  public void setJsonUtils(JsonUtils jsonUtils) {
    this.jsonUtils = jsonUtils;
  }

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setAmazonS3Utils(AmazonS3Utils amazonS3Utils) {
    this.amazonS3Utils = amazonS3Utils;
  }

  protected List<S3ObjectSummary> getSummaries(String subfolder) {
    ListObjectsV2Request request = amazonS3Utils.listObjectsV2Request(
        appConfig.bucketName(),
        subfolder
    );
    ListObjectsV2Result result = amazonS3Utils.listObjectsV2(request);
    return result.getObjectSummaries();
  }

  protected void saveCsvToS3(String filename, String content) {
    amazonS3Utils.putObject(
        appConfig.bucketName(),
        Constants.SUBFOLDER_CSVS_NAME + "/" + filename,
        content
    );
  }

  protected DecodedJWT decodeAccessToken(String accessToken) {
    byte[] bytes = base64Utils.decode(accessToken);

    String jwt = threefishDecrypter.decrypt(
        appConfig.threefishSecret(),
        bytes
    );

    return accessTokenParser.parse(jwt);
  }
}
