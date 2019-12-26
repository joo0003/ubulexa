package es.ubu.ubulexa.core.tools.resolvers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.daos.FirstTimeUserEventDao;
import es.ubu.ubulexa.core.tools.voicetransformers.AlexaVoiceTransformer;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class VoiceTransformerResolver {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      VoiceTransformerResolver.class);

  private PetiteContainer petiteContainer;
  private FirstTimeUserEventDao firstTimeUserEventDao;

  @PetiteInject
  public void setFirstTimeUserEventDao(FirstTimeUserEventDao firstTimeUserEventDao) {
    this.firstTimeUserEventDao = firstTimeUserEventDao;
  }

  @PetiteInject
  public void setPetiteContainer(PetiteContainer petiteContainer) {
    this.petiteContainer = petiteContainer;
  }

  public VoiceTransformer resolve(HandlerInput handlerInput) {
    /*
    try {
      Integer count = firstTimeUserEventDao.countAll();
      if (null != count && count % 2 == 0) {
        return petiteContainer.getBean(EnriqueVoiceTransformer.class);
      }
      return petiteContainer.getBean(ConchitaVoiceTransformer.class);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }
     */
    return petiteContainer.getBean(AlexaVoiceTransformer.class);
  }
}
