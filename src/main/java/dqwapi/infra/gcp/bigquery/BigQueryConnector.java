package dqwapi.infra.gcp.bigquery;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"dwh-gcp-bigquery-prod", "dwh-gcp-bigquery-dev"})
@Slf4j
@Component
public class BigQueryConnector implements IBigQueryConnector {

  final BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

  @Value("${gcp.project-id}")
  private String projectId;

  @Value("${gcp.big-query.dataset}")
  private String datasetName;

  @Value("${gcp.big-query.table}")
  private String tableName;

  @Override
  public TableResult query(final String query) {
    final String replacedQuery = query.replace("$projectId", projectId)
        .replace("$datasetName", datasetName)
        .replace("$tableName", tableName);
    final QueryJobConfiguration queryJobConfiguration =
        QueryJobConfiguration.newBuilder(replacedQuery).build();
    try {
      return bigquery.query(queryJobConfiguration);
    } catch (InterruptedException e) {
      throw new IllegalStateException("Failed to query: " + query, e);
    }
  }
}
