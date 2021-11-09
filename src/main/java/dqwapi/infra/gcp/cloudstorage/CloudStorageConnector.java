package dqwapi.infra.gcp.cloudstorage;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CloudStorageConnector implements ICloudStorageConnector {

  // TODO:
  @PostConstruct
  void init() {
    Storage storage = StorageOptions.getDefaultInstance().getService();
    String bucketName = "dqwapi";
    Bucket bucket = storage.get(bucketName);
    log.info(bucket.getLocation());
  }
}
