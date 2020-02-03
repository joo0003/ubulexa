package es.ubu.ubulexa.core.utils.time;

import java.time.Clock;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class ClockUtils {

  public Clock clock() {
    //return Clock.fixed(Instant.ofEpochMilli(1582711994000L), Constants.CET_ZONE_ID);
    return Clock.systemUTC();
  }
}
