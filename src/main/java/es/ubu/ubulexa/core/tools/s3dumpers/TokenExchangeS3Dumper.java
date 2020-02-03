package es.ubu.ubulexa.core.tools.s3dumpers;

import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class TokenExchangeS3Dumper extends AbstractS3Dumper {

  public void dump(String uuid, byte[] bytes) {
    dump(uuid, Constants.SUBFOLDER_TOKEN_EXCHANGE_NAME, bytes);
  }
}
