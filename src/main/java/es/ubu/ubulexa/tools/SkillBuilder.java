package es.ubu.ubulexa.tools;

import com.amazon.ask.Skill;
import com.amazon.ask.builder.StandardSkillBuilder;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.exception.handler.GenericExceptionHandler;
import com.amazon.ask.request.handler.GenericRequestHandler;
import com.amazon.ask.request.interceptor.GenericRequestInterceptor;
import es.ubu.ubulexa.exceptionhandlers.NotAGeolocationDeviceExceptionHandler;
import es.ubu.ubulexa.requesthandlers.CancelIntentHandler;
import es.ubu.ubulexa.requesthandlers.HelpIntentHandler;
import es.ubu.ubulexa.requesthandlers.LaunchRequestHandler;
import es.ubu.ubulexa.requesthandlers.NavigateHomeIntentHandler;
import es.ubu.ubulexa.requesthandlers.SessionEndedRequestHandler;
import es.ubu.ubulexa.requesthandlers.StopIntentHandler;
import es.ubu.ubulexa.requestinterceptors.MainRequestInterceptor;
import es.ubu.ubulexa.utils.SkillsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class SkillBuilder {

  private SkillsUtils skillsUtils;
  private SystemEnvReader systemEnvReader;
  private PetiteContainer petiteContainer;

  @PetiteInject
  public void setSkillsUtils(SkillsUtils skillsUtils) {
    this.skillsUtils = skillsUtils;
  }

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  @PetiteInject
  public void setPetiteContainer(PetiteContainer petiteContainer) {
    this.petiteContainer = petiteContainer;
  }

  public Skill build() {
    StandardSkillBuilder standardSkillBuilder = skillsUtils.standard();
    standardSkillBuilder.withSkillId(systemEnvReader.skillId());
    standardSkillBuilder.addRequestHandlers(requestHandlers());
    standardSkillBuilder.addRequestInterceptors(requestInterceptors());
    standardSkillBuilder.addExceptionHandlers(exceptionHandlers());
    return standardSkillBuilder.build();
  }

  private List<GenericExceptionHandler<HandlerInput, Optional<Response>>> exceptionHandlers() {
    List<GenericExceptionHandler<HandlerInput, Optional<Response>>> l = new ArrayList<>();
    l.add(petiteContainer.getBean(NotAGeolocationDeviceExceptionHandler.class));
    return l;
  }

  private List<GenericRequestInterceptor<HandlerInput>> requestInterceptors() {
    List<GenericRequestInterceptor<HandlerInput>> l = new ArrayList<>();
    l.add(petiteContainer.getBean(MainRequestInterceptor.class));
    return l;
  }

  private List<GenericRequestHandler<HandlerInput, Optional<Response>>> requestHandlers() {
    List<GenericRequestHandler<HandlerInput, Optional<Response>>> l = new ArrayList<>();
    l.add(petiteContainer.getBean(LaunchRequestHandler.class));
    l.add(petiteContainer.getBean(SessionEndedRequestHandler.class));
    l.add(petiteContainer.getBean(HelpIntentHandler.class));
    l.add(petiteContainer.getBean(CancelIntentHandler.class));
    l.add(petiteContainer.getBean(StopIntentHandler.class));
    l.add(petiteContainer.getBean(NavigateHomeIntentHandler.class));
    return l;
  }
}
