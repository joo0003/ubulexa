package es.ubu.ubulexa.core.tools.s3dumpers;

import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class SkillResponseS3Dumper extends AbstractS3Dumper {

  public void dump(String uuid, byte[] bytes) {
    dump(uuid, Constants.SUBFOLDER_RAW_RESPONSES_NAME, bytes);
  }
}
