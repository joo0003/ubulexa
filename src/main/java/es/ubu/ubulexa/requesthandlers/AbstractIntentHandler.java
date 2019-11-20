package es.ubu.ubulexa.requesthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.requesthandlers.matchers.Matcher;
import es.ubu.ubulexa.requesthandlers.models.HandlerModel;
import es.ubu.ubulexa.requesthandlers.matchers.Matcher;
import es.ubu.ubulexa.requesthandlers.models.HandlerModel;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractIntentHandler implements RequestHandler {

  private Matcher matcher;
  private HandlerModel handlerModel;

  public void setMatcher(Matcher matcher) {
    this.matcher = matcher;
  }

  public void setHandlerModel(HandlerModel handlerModel) {
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