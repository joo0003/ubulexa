package es.ubu.ubulexa.utils;

import java.time.LocalDateTime;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class LocalDateTimeUtils {

  private ClockUtils clockUtils;

  @PetiteInject
  public void setClockUtils(ClockUtils clockUtils) {
    this.clockUtils = clockUtils;
  }

  public LocalDateTime nowUtc() {
    return LocalDateTime.now(clockUtils.clock());
  }
}
