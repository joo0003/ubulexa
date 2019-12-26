package es.ubu.ubulexa.core.tools.resolvers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import java.io.InputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class DictionaryPropsResolver {

  private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryPropsResolver.class);

  private DictionaryFileResolver dictionaryFileResolver;
  private UserLocaleResolver userLocaleResolver;

  @PetiteInject
  public void setDictionaryFileResolver(
      DictionaryFileResolver dictionaryFileResolver) {
    this.dictionaryFileResolver = dictionaryFileResolver;
  }

  @PetiteInject
  public void setUserLocaleResolver(
      UserLocaleResolver userLocaleResolver) {
    this.userLocaleResolver = userLocaleResolver;
  }

  public Props resolve(HandlerInput handlerInput) {
    try {
      String locale = userLocaleResolver.resolve(handlerInput);

      InputStream is = dictionaryFileResolver.resolve(locale);

      Props props = new Props();
      props.load(is);
      return props;
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
    return null;
  }
}
