package es.ubu.ubulexa.core.utils.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class LocalDateUtils {

  private LocalDateTimeUtils localDateTimeUtils;

  @PetiteInject
  public void setLocalDateTimeUtils(LocalDateTimeUtils localDateTimeUtils) {
    this.localDateTimeUtils = localDateTimeUtils;
  }

  public boolean isToday(LocalDate ld1, LocalDate ld2) {
    return null != ld1 && null != ld2 && ld1.isEqual(ld2);
  }

  public LocalDate from(Instant instant, ZoneId zoneId) {
    return localDateTimeUtils.from(instant, zoneId).toLocalDate();
  }

  public LocalDate parse(String str) {
    return LocalDate.parse(str);
  }
}
