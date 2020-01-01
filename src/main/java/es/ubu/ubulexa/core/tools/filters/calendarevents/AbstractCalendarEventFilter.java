package es.ubu.ubulexa.core.tools.filters.calendarevents;

import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.time.InstantUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractCalendarEventFilter {

  private AppConfig appConfig;
  private InstantUtils instantUtils;

  @PetiteInject
  public void setInstantUtils(InstantUtils instantUtils) {
    this.instantUtils = instantUtils;
  }

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  public AppConfig appConfig() {
    return appConfig;
  }

  public InstantUtils instantUtils() {
    return instantUtils;
  }
}
