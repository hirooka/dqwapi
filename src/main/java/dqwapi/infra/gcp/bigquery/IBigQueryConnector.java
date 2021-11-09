package dqwapi.infra.gcp.bigquery;

import com.google.cloud.bigquery.TableResult;

public interface IBigQueryConnector {
  TableResult query(final String query);
}
