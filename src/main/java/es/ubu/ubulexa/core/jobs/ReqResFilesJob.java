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
import jodd.util.StringUtil;

@PetiteBean
public class ReqResFilesJob extends AbstractJob {

  public void run() {
    String csv = new String();

    csv += "user-id";
    csv += StringPool.COMMA;
    csv += "username";
    csv += StringPool.COMMA;

    csv += "request-creation-time-UTC";
    csv += StringPool.COMMA;
    csv += "request-creation-time-CET";
    csv += StringPool.COMMA;

    csv += "response-creation-time-UTC";
    csv += StringPool.COMMA;
    csv += "response-creation-time-CET";
    csv += StringPool.COMMA;

    csv += "request-type";
    csv += StringPool.COMMA;
    csv += "request-locale";
    csv += StringPool.COMMA;
    csv += "request-first-time";
    csv += StringPool.COMMA;
    csv += "request-should-end-session";
    csv += StringPool.COMMA;

    csv += "session-id";
    csv += StringPool.COMMA;
    csv += "is-new-session";
    csv += StringPool.COMMA;

    csv += "viewport-shape";
    csv += StringPool.COMMA;
    csv += "viewport-width";
    csv += StringPool.COMMA;
    csv += "viewport-height";
    csv += StringPool.COMMA;
    csv += "viewport-dpi";
    csv += StringPool.COMMA;
    csv += "viewport-codec";
    csv += StringPool.COMMA;

    csv += "response-speech";
    csv += StringPool.NEWLINE;

    List<S3ObjectSummary> requestSummaries = getSummaries(Constants.SUBFOLDER_RAW_REQUESTS_NAME);
    List<S3ObjectSummary> responseSummaries = getSummaries(Constants.SUBFOLDER_RAW_RESPONSES_NAME);

    for (S3ObjectSummary requestSummary : requestSummaries) {
      S3ObjectSummary responseSummary = findResponseSummary(requestSummary, responseSummaries);

      if (null == responseSummary) {
        continue;
      }

      JsonObject reqJson = summaryToJson(requestSummary);

      ZonedDateTime reqFileInstant = requestSummary.getLastModified().toInstant()
          .atZone(ZoneOffset.UTC);

      ZonedDateTime spanishReqFileInstant = requestSummary.getLastModified().toInstant()
          .atZone(Constants.CET_ZONE_ID);

      JsonObject resJson = summaryToJson(responseSummary);

      ZonedDateTime resFileInstant = responseSummary.getLastModified().toInstant()
          .atZone(ZoneOffset.UTC);

      ZonedDateTime spanishResFileInstant = responseSummary.getLastModified().toInstant()
          .atZone(Constants.CET_ZONE_ID);

      String accessToken = reqJson.getString("session.user.accessToken");

      if (StringUtil.isBlank(accessToken)) {
        continue;
      }

      DecodedJWT decodedJWT = decodeAccessToken(accessToken);

      String userId = decodedJWT.getClaim(Constants.JWT_USER_ID_CLAIM).asString();
      String username = decodedJWT.getClaim(Constants.JWT_USERNAME_CLAIM).asString();

      csv += userId;
      csv += StringPool.COMMA;
      csv += username;
      csv += StringPool.COMMA;

      csv += reqFileInstant.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.COMMA;
      csv += spanishReqFileInstant.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.COMMA;

      csv += resFileInstant.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.COMMA;
      csv += spanishResFileInstant.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.COMMA;

      csv += reqJson.getString("request.type", StringPool.EMPTY);
      csv += StringPool.COMMA;
      csv += reqJson.getString("request.locale", StringPool.EMPTY);
      csv += StringPool.COMMA;
      csv += resJson.getString("sessionAttributes.firstTime", StringPool.EMPTY);
      csv += StringPool.COMMA;
      csv += StringUtil.toSafeString(resJson.getBoolean("response.shouldEndSession", null));
      csv += StringPool.COMMA;

      csv += reqJson.getString("session.sessionId", StringPool.EMPTY);
      csv += StringPool.COMMA;
      csv += StringUtil.toSafeString(reqJson.getBoolean("session.new", null));
      csv += StringPool.COMMA;

      csv += reqJson.getString("context.Viewport.shape", StringPool.EMPTY);
      csv += StringPool.COMMA;
      csv += reqJson.getInteger("context.Viewport.pixelWidth", 0);
      csv += StringPool.COMMA;
      csv += reqJson.getInteger("context.Viewport.pixelHeight", 0);
      csv += StringPool.COMMA;
      csv += reqJson.getInteger("context.Viewport.dpi", 0);
      csv += StringPool.COMMA;
      csv += reqJson.getString("context.Viewport.video.codecs[0]", StringPool.EMPTY);
      csv += StringPool.COMMA;

      csv += StringPool.QUOTE + resJson.getString("response.outputSpeech.ssml", StringPool.EMPTY)
          + StringPool.QUOTE;
      csv += StringPool.NEWLINE;
    }

    saveCsvToS3(Constants.DATA_CSV_FILENAME, csv);
  }

  private JsonObject summaryToJson(S3ObjectSummary summary) {
    String content = amazonS3Utils
        .getObjectAsString(summary.getBucketName(), summary.getKey());

    return jsonUtils.jsonParser().parseAsJsonObject(jsonUtils.flatten(content));
  }

  private S3ObjectSummary findResponseSummary(
      S3ObjectSummary requestSummary,
      List<S3ObjectSummary> responseSummaries
  ) {
    String requestUid = StringUtil.cutFromIndexOf(requestSummary.getKey(), StringPool.SLASH);
    requestUid = StringUtil.cutFromIndexOf(requestUid, StringPool.UNDERSCORE);

    for (S3ObjectSummary responseSummary : responseSummaries) {
      String responseUid = StringUtil.cutFromIndexOf(responseSummary.getKey(), StringPool.SLASH);
      responseUid = StringUtil.cutFromIndexOf(responseUid, StringPool.UNDERSCORE);

      if (StringUtil.equals(requestUid, responseUid)) {
        return responseSummary;
      }
    }
    return null;
  }
}
