package dqwapi.infra.gcp.cloudstorage;

public interface ICloudStorageConnector {
  void uploadObject(final String object);
}
