package es.ubu.ubulexa.skill.handlers.exceptions;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.skill.handlers.exceptions.matchers.ExceptionMatcher;
import es.ubu.ubulexa.skill.handlers.exceptions.models.ExceptionHandlerModel;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractExceptionHandler implements ExceptionHandler {

  private ExceptionMatcher matcher;
  private ExceptionHandlerModel handlerModel;

  public void setMatcher(ExceptionMatcher matcher) {
    this.matcher = matcher;
  }

  public void setHandlerModel(ExceptionHandlerModel handlerModel) {
    this.handlerModel = handlerModel;
  }

  @Override
  public boolean canHandle(HandlerInput handlerInput, Throwable throwable) {
    return matcher.match(handlerInput, throwable);
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput, Throwable throwable) {
    return handlerModel.handle(handlerInput, throwable);
  }
}