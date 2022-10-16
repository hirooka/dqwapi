package dqwapi.infra.gcp.cloudstorage;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CloudStorageConnector implements ICloudStorageConnector {

  @Autowired
  private Environment environment;

  @Value("${gcp.cloud-storage.bucket}")
  private String bucketName;

  final Storage storage = StorageOptions.getDefaultInstance().getService();

  @Override
  public void uploadObject(String object) {
    final String objectName;
    if (Arrays.asList(environment.getActiveProfiles()).contains("dwh-gcp-bigquery-prod")) {
      objectName = "kokoro-flat-nd.json";
    } else {
      objectName = "kokoro-flat-nd-dev.json";
    }
    final BlobId blobId = BlobId.of(bucketName, objectName);
    final BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.create(blobInfo, object.getBytes(StandardCharsets.UTF_8));
    log.info("Uploaded {}.", objectName);
  }
}
