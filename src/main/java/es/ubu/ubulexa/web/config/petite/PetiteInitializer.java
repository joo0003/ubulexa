package es.ubu.ubulexa.web.config.petite;

import jodd.petite.AutomagicPetiteConfigurator;
import jodd.petite.PetiteContainer;

public class PetiteInitializer {

  public static PetiteContainer init() {
    PetiteContainer petiteContainer = new PetiteContainer();
    petiteContainer.config().setUseFullTypeNames(true);
    petiteContainer.addSelf();

    AutomagicPetiteConfigurator configurator = new AutomagicPetiteConfigurator(petiteContainer);
    configurator.configure();

    return petiteContainer;
  }
}
