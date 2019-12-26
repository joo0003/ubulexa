package es.ubu.ubulexa.core.tools.voicetransformers;

import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractVoiceTransformer implements VoiceTransformer {

  @Override
  public abstract String transform(String str);
}
