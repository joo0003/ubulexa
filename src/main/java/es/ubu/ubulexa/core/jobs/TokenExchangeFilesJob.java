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
import es.ubu.ubulexa.core.utils.LoggerUtils;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import jodd.json.JsonObject;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringPool;

@PetiteBean
public class TokenExchangeFilesJob {

  private AppConfig appConfig;
  private AmazonS3Utils amazonS3Utils;
  private JsonUtils jsonUtils;
  private Base64Utils base64Utils;
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
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  @PetiteInject
  public void setJsonUtils(JsonUtils jsonUtils) {
    this.jsonUtils = jsonUtils;
  }

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setAmazonS3Utils(AmazonS3Utils amazonS3Utils) {
    this.amazonS3Utils = amazonS3Utils;
  }

  public void run() {
    String csv = new String();
    csv += "userId";
    csv += StringPool.COMMA;
    csv += "username";
    csv += StringPool.COMMA;
    csv += "issuedAt-UTC";
    csv += StringPool.COMMA;
    csv += "issuedAt-CET";
    csv += StringPool.NEWLINE;

    List<S3ObjectSummary> summaries = getSummaries();

    for (S3ObjectSummary summary : summaries) {
      DecodedJWT decodedJWT = getDecodedJwt(summary);

      String userId = decodedJWT.getClaim(Constants.JWT_USER_ID_CLAIM).asString();
      String username = decodedJWT.getClaim(Constants.JWT_USERNAME_CLAIM).asString();

      ZonedDateTime issuedAtInstant = decodedJWT.getIssuedAt().toInstant()
          .atZone(ZoneOffset.UTC);

      ZonedDateTime spanishIssuedAtInstant = decodedJWT.getIssuedAt().toInstant()
          .atZone(Constants.CET_ZONE_ID);

      csv += userId;
      csv += StringPool.COMMA;
      csv += username;
      csv += StringPool.COMMA;
      csv += issuedAtInstant.toString();
      csv += StringPool.COMMA;
      csv += spanishIssuedAtInstant.toString();
      csv += StringPool.NEWLINE;
    }

    LoggerUtils.info(csv);
    amazonS3Utils.putObject(
        appConfig.bucketName(),
        Constants.SUBFOLDER_CSVS_NAME + "/token_exchange.csv",
        csv
    );
  }

  private DecodedJWT getDecodedJwt(S3ObjectSummary summary) {
    String content = amazonS3Utils.getObjectAsString(summary.getBucketName(), summary.getKey());

    JsonObject jsonObject = jsonUtils.jsonParser().parseAsJsonObject(content);

    String accessToken = jsonObject.getString("access_token");

    byte[] bytes = base64Utils.decode(accessToken);

    String jwt = threefishDecrypter.decrypt(
        appConfig.threefishSecret(),
        bytes
    );

    return accessTokenParser.parse(jwt);
  }

  private List<S3ObjectSummary> getSummaries() {
    ListObjectsV2Request request = amazonS3Utils.listObjectsV2Request(
        appConfig.bucketName(),
        Constants.SUBFOLDER_TOKEN_EXCHANGE_NAME
    );
    ListObjectsV2Result result = amazonS3Utils.listObjectsV2(request);
    return result.getObjectSummaries();
  }
}
