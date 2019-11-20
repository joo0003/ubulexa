package es.ubu.ubulexa;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import es.ubu.ubulexa.tools.PetiteInitializer;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import jodd.petite.PetiteContainer;

public abstract class AbstractLambdaHandler implements RequestStreamHandler {

  protected static PetiteContainer petiteContainer;

  protected void initPetiteContainer() {
    if (null == petiteContainer) {
      petiteContainer = PetiteInitializer.init();
      postInit(petiteContainer);
    }
  }

  private void postInit(PetiteContainer petiteContainer) {
    petiteContainer.getBean(AmazonS3Utils.class).setAmazonS3(AmazonS3ClientBuilder.defaultClient());
  }
}
