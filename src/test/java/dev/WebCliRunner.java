package dev;

import dev.config.PetitePostInitializer;
import es.ubu.ubulexa.skill.config.petite.PetiteInitializer;
import es.ubu.ubulexa.web.config.SparkResources;
import jodd.petite.PetiteContainer;
import spark.Spark;

public class WebCliRunner {

  private static PetiteContainer petiteContainer;

  public static void main(String[] args) {
    if (null == petiteContainer) {
      petiteContainer = PetiteInitializer.init();
      PetitePostInitializer.init(petiteContainer);
    }

    petiteContainer.getBean(SparkResources.class).defineResources();
    Spark.awaitInitialization();
  }
}
