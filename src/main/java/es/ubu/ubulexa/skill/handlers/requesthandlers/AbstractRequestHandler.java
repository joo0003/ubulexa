package es.ubu.ubulexa.skill.handlers.requesthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.skill.handlers.requesthandlers.matchers.RequestMatcher;
import es.ubu.ubulexa.skill.handlers.requesthandlers.models.RequestHandlerModel;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractRequestHandler implements RequestHandler {

  private RequestMatcher matcher;
  private RequestHandlerModel handlerModel;

  public void setMatcher(RequestMatcher matcher) {
    this.matcher = matcher;
  }

  public void setHandlerModel(RequestHandlerModel handlerModel) {
    this.handlerModel = handlerModel;
  }

  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return matcher.match(handlerInput);
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    return handlerModel.handle(handlerInput);
  }
}