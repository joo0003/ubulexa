package es.ubu.ubulexa.core.utils;

import java.io.IOException;
import java.io.InputStream;
import jodd.io.StreamUtil;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class StreamUtils {

  public byte[] readBytes(InputStream inputStream) throws IOException {
    return StreamUtil.readBytes(inputStream);
  }
}
