package es.ubu.ubulexa.core.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
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

  public boolean doesObjectExist(String bucket, String key) {
    return amazonS3.doesObjectExist(bucket, key);
  }

  public void putObject(String bucket, String key, String content) {
    amazonS3.putObject(bucket, key, content);
  }

  public void putObject(String bucket, String key, byte[] bytes) throws IOException {
    String content = ioUtils.toString(bytes);
    putObject(bucket, key, content);
  }

  public ListObjectsV2Result listObjectsV2(String bucket, String prefix) {
    return amazonS3.listObjectsV2(bucket, prefix);
  }
}
