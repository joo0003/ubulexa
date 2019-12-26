package es.ubu.ubulexa.core.utils;

import jodd.http.HttpRequest;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class HttpRequestUtils {

  public HttpRequest get(String url) {
    return HttpRequest.get(url);
  }
}
