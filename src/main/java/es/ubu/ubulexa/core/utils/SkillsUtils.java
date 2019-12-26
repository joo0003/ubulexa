package es.ubu.ubulexa.core.utils;

import com.amazon.ask.Skills;
import com.amazon.ask.builder.StandardSkillBuilder;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class SkillsUtils {

  public StandardSkillBuilder standard() {
    return Skills.standard();
  }
}
