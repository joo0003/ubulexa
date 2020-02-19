package es.ubu.ubulexa.core.jobs;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import es.ubu.ubulexa.core.utils.Base64Utils;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringPool;

@PetiteBean
public class FirstTimeFilesJob extends AbstractJob {

  public void run() {
    String csv = new String();
    csv += "email";
    csv += StringPool.COMMA;
    csv += "creationTime-UTC";
    csv += StringPool.COMMA;
    csv += "creationTime-CET";
    csv += StringPool.NEWLINE;

    List<S3ObjectSummary> summaries = getSummaries(Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME);

    for (S3ObjectSummary summary : summaries) {
      String content = amazonS3Utils.getObjectAsString(summary.getBucketName(), summary.getKey());

      String email = new String(base64Utils.decode(content), StandardCharsets.UTF_8);

      ZonedDateTime utcCreationTime = summary.getLastModified().toInstant().atZone(ZoneOffset.UTC);

      ZonedDateTime spanishCreationTime = summary.getLastModified().toInstant()
          .atZone(Constants.CET_ZONE_ID);

      csv += email;
      csv += StringPool.COMMA;
      csv += utcCreationTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.COMMA;
      csv += spanishCreationTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      csv += StringPool.NEWLINE;
    }

    saveCsvToS3(Constants.FIRST_TIME_USER_EVENTS_CSV_FILENAME, csv);
  }
}
