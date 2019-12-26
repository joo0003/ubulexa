package dev.config;

import static org.mockito.Mockito.when;

import es.ubu.ubulexa.core.tools.SystemEnvReader;
import es.ubu.ubulexa.core.tools.SystemEnvReader.SystemEnvKey;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import jodd.petite.PetiteContainer;
import org.mockito.Mockito;

public class PetitePostInitializer {

  public static void init(PetiteContainer petiteContainer) {
    petiteContainer.removeBean(AmazonS3Utils.class);
    petiteContainer.addBean(AmazonS3Utils.class.getName(), buildMockAmazonS3Utils());

    petiteContainer.removeBean(SystemEnvReader.class);
    petiteContainer.addBean(SystemEnvReader.class.getName(), buildMockSystemEnvReader());
  }

  private static AmazonS3Utils buildMockAmazonS3Utils() {
    return Mockito.mock(AmazonS3Utils.class);
  }

  private static SystemEnvReader buildMockSystemEnvReader() {
    SystemEnvReader systemEnvReader = Mockito.mock(SystemEnvReader.class);
    when(systemEnvReader.bucketName()).thenReturn(SystemEnvKey.BUCKET_NAME.name());
    when(systemEnvReader.skillId()).thenReturn(SystemEnvKey.SKILL_ID.name());
    when(systemEnvReader.jwtSecret()).thenReturn(SystemEnvKey.JWT_SECRET.name());
    when(systemEnvReader.moodleHostUrl()).thenReturn("https://ubuvirtual.ubu.es/");
    return systemEnvReader;
  }
}
