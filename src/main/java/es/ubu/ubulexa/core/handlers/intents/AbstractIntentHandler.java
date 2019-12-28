package es.ubu.ubulexa.core.handlers.intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.handlers.intents.matchers.IntentMatcher;
import es.ubu.ubulexa.core.handlers.intents.models.IntentHandlerModel;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractIntentHandler implements RequestHandler {

  private IntentMatcher matcher;
  private IntentHandlerModel handlerModel;

  public void setMatcher(IntentMatcher matcher) {
    this.matcher = matcher;
  }

  public void setHandlerModel(IntentHandlerModel handlerModel) {
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