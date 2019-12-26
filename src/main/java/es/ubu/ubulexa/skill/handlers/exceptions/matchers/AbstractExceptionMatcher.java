package es.ubu.ubulexa.skill.handlers.exceptions.matchers;


import es.ubu.ubulexa.core.exceptions.SkillException;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public abstract class AbstractExceptionMatcher implements ExceptionMatcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      AbstractExceptionMatcher.class);

  protected boolean match(Throwable throwable, Class<? extends SkillException> clazz) {
    try {
      return clazz.isAssignableFrom(throwable.getClass());
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
      return false;
    }
  }
}