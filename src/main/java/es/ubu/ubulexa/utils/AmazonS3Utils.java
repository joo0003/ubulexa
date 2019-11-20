package es.ubu.ubulexa.utils;

import com.amazonaws.services.s3.AmazonS3;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class AmazonS3Utils {

  private AmazonS3 amazonS3;

  public void setAmazonS3(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
  }

  public void putObject(String bucket, String key, String content) {
    amazonS3.putObject(bucket, key, content);
  }
}
