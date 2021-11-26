package dqwapi.infra.gcp.cloudstorage;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CloudStorageConnector implements ICloudStorageConnector {

  @Value("${gcp.cloud-storage.bucket}")
  private String bucketName;

  final Storage storage = StorageOptions.getDefaultInstance().getService();
  final String objectName = "kokoro-flat-nd.json";

  @Override
  public void uploadObject(String object) {
    final BlobId blobId = BlobId.of(bucketName, objectName);
    final BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.create(blobInfo, object.getBytes(StandardCharsets.UTF_8));
    log.info("Uploaded {}.", objectName);
  }
}
