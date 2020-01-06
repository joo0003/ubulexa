package es.ubu.ubulexa.core.utils.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class LocalDateTimeUtils {

  public LocalDateTime from(Instant instant, ZoneId zoneId) {
    return LocalDateTime.ofInstant(instant, zoneId);
  }
}
