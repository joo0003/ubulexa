package es.ubu.ubulexa.exceptionhandlers.models;

import es.ubu.ubulexa.tools.resolvers.DictionaryPropsResolver;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractExceptionHandlerModel implements HandlerModel {

  protected DictionaryPropsResolver dictionaryPropsResolver;

  @PetiteInject
  public void setDictionaryPropsResolver(
      DictionaryPropsResolver dictionaryPropsResolver) {
    this.dictionaryPropsResolver = dictionaryPropsResolver;
  }
}