package es.ubu.ubulexa.core.jobs;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.core.Constants;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jodd.json.JsonObject;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringPool;

@PetiteBean
public class TokenExchangeFilesJob extends AbstractJob {

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

    List<S3ObjectSummary> summaries = getSummaries(Constants.SUBFOLDER_TOKEN_EXCHANGE_NAME);

    for (S3ObjectSummary summary : summaries) {
      String accessToken = extractAccessToken(summary);
      DecodedJWT decodedJWT = decodeAccessToken(accessToken);

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
      csv += issuedAtInstant.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.COMMA;
      csv += spanishIssuedAtInstant.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.NEWLINE;
    }

    saveCsvToS3(Constants.TOKEN_EXCHANGE_CSV_FILENAME, csv);
  }

  private String extractAccessToken(S3ObjectSummary summary) {
    String content = amazonS3Utils.getObjectAsString(summary.getBucketName(), summary.getKey());

    JsonObject jsonObject = jsonUtils.jsonParser().parseAsJsonObject(content);

    return jsonObject.getString("access_token");
  }
}
