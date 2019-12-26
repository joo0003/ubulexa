package es.ubu.ubulexa.core.pojos;

import es.ubu.ubulexa.skill.handlers.HandlerModel;

public class ResponseTemplate {

  private Class<? extends HandlerModel> clazz;
  private boolean endSession = true;

  public boolean isEndSession() {
    return endSession;
  }

  public void setEndSession(boolean endSession) {
    this.endSession = endSession;
  }

  public Class<? extends HandlerModel> getClazz() {
    return clazz;
  }

  public void setClazz(Class<? extends HandlerModel> clazz) {
    this.clazz = clazz;
  }
}
