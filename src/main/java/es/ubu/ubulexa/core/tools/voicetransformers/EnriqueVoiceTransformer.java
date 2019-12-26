package es.ubu.ubulexa.core.tools.voicetransformers;

import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class EnriqueVoiceTransformer extends AbstractVoiceTransformer {

  @Override
  public String transform(String str) {
    return "<voice name=\"" + Constants.VOICE_ENRIQUE + "\">" + str + "</voice>";
  }
}
