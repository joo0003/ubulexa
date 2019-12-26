package es.ubu.ubulexa.skill.responsefactories;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import java.util.Optional;

public interface ResponseFactory {

  Optional<Response> build(HandlerInput handlerInput, ResponseTemplate responseTemplate);
}
