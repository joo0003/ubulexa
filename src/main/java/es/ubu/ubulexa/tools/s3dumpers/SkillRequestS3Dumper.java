package es.ubu.ubulexa.tools.s3dumpers;

import es.ubu.ubulexa.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class SkillRequestS3Dumper extends AbstractS3Dumper {

  public void dump(String uuid, byte[] bytes) {
    super.dump(uuid, Constants.SUBFOLDER_RAW_REQUESTS_NAME, bytes);
  }
}
