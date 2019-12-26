package es.ubu.ubulexa.core.utils;

import java.io.IOException;
import java.io.InputStream;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class PropsUtils {

  public Props load(InputStream is) throws IOException {
    return new Props().load(is);
  }
}
