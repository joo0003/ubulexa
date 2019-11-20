package es.ubu.ubulexa.requesthandlers.models;

import es.ubu.ubulexa.tools.resolvers.DictionaryPropsResolver;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractRequestHandlerModel implements HandlerModel {

  protected DictionaryPropsResolver dictionaryPropsResolver;

  @PetiteInject
  public void setDictionaryPropsResolver(
      DictionaryPropsResolver dictionaryPropsResolver) {
    this.dictionaryPropsResolver = dictionaryPropsResolver;
  }
}