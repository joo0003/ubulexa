package es.ubu.ubulexa.skill.handlers.exceptions.models;

import es.ubu.ubulexa.skill.handlers.AbstractHandlerModel;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractExceptionHandlerModel
    extends AbstractHandlerModel
    implements ExceptionHandlerModel {

}