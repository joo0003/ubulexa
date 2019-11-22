package es.ubu.ubulexa.config.petite;

import jodd.petite.AutomagicPetiteConfigurator;
import jodd.petite.PetiteContainer;

public class PetiteInitializer {

  public static PetiteContainer init() {
    PetiteContainer petiteContainer = new PetiteContainer();
    petiteContainer.config().setUseFullTypeNames(true);

    AutomagicPetiteConfigurator configurator = new AutomagicPetiteConfigurator(petiteContainer);
    configurator.configure();

    return petiteContainer;
  }
}
