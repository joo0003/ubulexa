package es.ubu.ubulexa.skill.config;

import com.amazon.ask.Skill;
import com.amazon.ask.builder.StandardSkillBuilder;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.exception.handler.GenericExceptionHandler;
import com.amazon.ask.request.handler.GenericRequestHandler;
import com.amazon.ask.request.interceptor.GenericRequestInterceptor;
import es.ubu.ubulexa.core.handlers.intents.CalendarIntentHandler;
import es.ubu.ubulexa.core.handlers.intents.CancelIntentHandler;
import es.ubu.ubulexa.core.handlers.intents.FallbackIntentHandler;
import es.ubu.ubulexa.core.handlers.intents.FirstTimeIntentHandler;
import es.ubu.ubulexa.core.handlers.intents.HelpIntentHandler;
import es.ubu.ubulexa.core.handlers.intents.NavigateHomeIntentHandler;
import es.ubu.ubulexa.core.handlers.intents.StopIntentHandler;
import es.ubu.ubulexa.core.handlers.intents.VoiceChangeIntentHandler;
import es.ubu.ubulexa.core.handlers.requesthandlers.LaunchRequestHandler;
import es.ubu.ubulexa.core.handlers.requesthandlers.SessionEndedRequestHandler;
import es.ubu.ubulexa.core.requestinterceptors.MainRequestInterceptor;
import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.SkillsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class SkillBuilder {

  private SkillsUtils skillsUtils;
  private AppConfig appConfig;
  private PetiteContainer petiteContainer;

  @PetiteInject
  public void setSkillsUtils(SkillsUtils skillsUtils) {
    this.skillsUtils = skillsUtils;
  }

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setPetiteContainer(PetiteContainer petiteContainer) {
    this.petiteContainer = petiteContainer;
  }

  public Skill build() {
    StandardSkillBuilder standardSkillBuilder = skillsUtils.standard();
    standardSkillBuilder.withSkillId(appConfig.skillId());
    standardSkillBuilder.addRequestHandlers(requestHandlers());
    standardSkillBuilder.addRequestInterceptors(requestInterceptors());
    standardSkillBuilder.addExceptionHandlers(exceptionHandlers());
    return standardSkillBuilder.build();
  }

  private List<GenericExceptionHandler<HandlerInput, Optional<Response>>> exceptionHandlers() {
    List<GenericExceptionHandler<HandlerInput, Optional<Response>>> l = new ArrayList<>();
    return l;
  }

  private List<GenericRequestInterceptor<HandlerInput>> requestInterceptors() {
    List<GenericRequestInterceptor<HandlerInput>> l = new ArrayList<>();
    l.add(petiteContainer.getBean(MainRequestInterceptor.class));
    return l;
  }

  private List<GenericRequestHandler<HandlerInput, Optional<Response>>> requestHandlers() {
    List<GenericRequestHandler<HandlerInput, Optional<Response>>> l = new ArrayList<>();
    l.add(petiteContainer.getBean(FirstTimeIntentHandler.class));
    l.add(petiteContainer.getBean(LaunchRequestHandler.class));
    l.add(petiteContainer.getBean(SessionEndedRequestHandler.class));
    l.add(petiteContainer.getBean(HelpIntentHandler.class));
    l.add(petiteContainer.getBean(CancelIntentHandler.class));
    l.add(petiteContainer.getBean(StopIntentHandler.class));
    l.add(petiteContainer.getBean(NavigateHomeIntentHandler.class));
    l.add(petiteContainer.getBean(FallbackIntentHandler.class));
    l.add(petiteContainer.getBean(VoiceChangeIntentHandler.class));
    l.add(petiteContainer.getBean(CalendarIntentHandler.class));
    return l;
  }
}
