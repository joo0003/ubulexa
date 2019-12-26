package es.ubu.ubulexa.skill.responsefactories.exceptions;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import es.ubu.ubulexa.skill.responsefactories.AbstractResponseFactory;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class NoFirstTimeUserEventFoundExceptionResponseFactory extends AbstractResponseFactory {

  @Override
  public Optional<Response> build(HandlerInput handlerInput, ResponseTemplate template) {
    ResponseBuilder builder = responseBuilder(handlerInput, template);

    VoiceTransformer voiceTransformer = resolveVoiceTransformer(handlerInput);

    Props dictionary = resolveDictionary(handlerInput);

    String speechText = dictionary.getValue(
        Constants.NO_FIRST_TIME_USER_EVENT_FOUND_EXCEPTION_SPEECH_TEXT_KEY
    );
    speechText = voiceTransformer.transform(speechText);

    builder.withSpeech(speechText);

    return builder.build();
  }
}
