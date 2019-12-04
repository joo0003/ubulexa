package es.ubu.ubulexa.utils;

import com.amazonaws.services.s3.AmazonS3;
import java.io.IOException;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class AmazonS3Utils {

  private AmazonS3 amazonS3;
  private IOUtils ioUtils;

  @PetiteInject
  public void setIoUtils(IOUtils ioUtils) {
    this.ioUtils = ioUtils;
  }

  public void setAmazonS3(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
  }

  public void putObject(String bucket, String key, String content) {
    amazonS3.putObject(bucket, key, content);
  }

  public void putObject(String bucket, String key, byte[] bytes) throws IOException {
    String content = ioUtils.toString(bytes);
    putObject(bucket, key, content);
  }
}
