package es.ubu.ubulexa.exceptionhandlers;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.exceptionhandlers.matchers.Matcher;
import es.ubu.ubulexa.exceptionhandlers.models.HandlerModel;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractExceptionHandler implements ExceptionHandler {

  private Matcher matcher;
  private HandlerModel handlerModel;

  public void setMatcher(Matcher matcher) {
    this.matcher = matcher;
  }

  public void setHandlerModel(HandlerModel handlerModel) {
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