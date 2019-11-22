package es.ubu.ubulexa.config.petite;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import jodd.petite.PetiteContainer;

public class PetitePostInitializer {

  public static void init(PetiteContainer petiteContainer) {
    petiteContainer.getBean(AmazonS3Utils.class).setAmazonS3(AmazonS3ClientBuilder.defaultClient());
  }
}
