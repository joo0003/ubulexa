package es.ubu.ubulexa.core.responsefactories.requests;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import es.ubu.ubulexa.core.responsefactories.AbstractResponseFactory;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class LaunchRequestResponseFactory extends AbstractResponseFactory {

  @Override
  public Optional<Response> build(HandlerInput handlerInput, ResponseTemplate template) {
    ResponseBuilder builder = responseBuilder(handlerInput, template);

    VoiceTransformer voiceTransformer = resolveVoiceTransformer(handlerInput);

    Props dictionary = resolveDictionary(handlerInput);

    String speechText = dictionary.getValue(
        Constants.LAUNCH_REQUEST_SPEECH_TEXT_KEY
    );
    speechText = voiceTransformer.transform(speechText);

    builder.withSpeech(speechText);

    return builder.build();
  }
}
