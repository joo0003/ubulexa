package es.ubu.ubulexa.core.tools.voicetransformers;

import jodd.petite.meta.PetiteBean;

@PetiteBean
public class AlexaVoiceTransformer extends AbstractVoiceTransformer {

  @Override
  public String transform(String str) {
    return str;
  }
}
