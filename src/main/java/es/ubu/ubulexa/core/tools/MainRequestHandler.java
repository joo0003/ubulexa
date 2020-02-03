package es.ubu.ubulexa.core.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.responsefactories.ResponseFactory;
import es.ubu.ubulexa.core.tools.resolvers.ResponseFactoryResolver;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class MainRequestHandler implements RequestHandler {

  private ResponseFactoryResolver responseFactoryResolver;

  @PetiteInject
  public void setResponseFactoryResolver(
      ResponseFactoryResolver responseFactoryResolver) {
    this.responseFactoryResolver = responseFactoryResolver;
  }

  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return true;
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    ResponseFactory responseFactory = responseFactoryResolver.resolve(handlerInput);
    if (null == responseFactory) {
      return Optional.empty();
    }
    return responseFactory.build(handlerInput);
  }
}
