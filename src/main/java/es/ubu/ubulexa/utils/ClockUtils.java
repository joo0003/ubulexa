package es.ubu.ubulexa.utils;

import java.time.Clock;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class ClockUtils {

  public Clock clock() {
    return Clock.systemUTC();
  }
}