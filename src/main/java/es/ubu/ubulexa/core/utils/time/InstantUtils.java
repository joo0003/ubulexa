package es.ubu.ubulexa.core.utils.time;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class InstantUtils {

  private ClockUtils clockUtils;

  @PetiteInject
  public void setClockUtils(ClockUtils clockUtils) {
    this.clockUtils = clockUtils;
  }

  public Instant now() {
    return clockUtils.clock().instant();
  }

  public Instant fromSecs(int secs) {
    return Instant.ofEpochMilli(TimeUnit.SECONDS.toMillis(secs));
  }
}
