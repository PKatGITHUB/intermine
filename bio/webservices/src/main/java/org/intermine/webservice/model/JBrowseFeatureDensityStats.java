package org.intermine.webservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JBrowseFeatureDensityStats
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-23T23:15:22.827+05:30[Asia/Kolkata]")
@JsonPropertyOrder({ "bins", "stats", "executionTime", "wasSuccessful", "error", "statusCode" })
public class JBrowseFeatureDensityStats extends JSONModel  {
  @JsonProperty("bins")
  @Valid
  private List<Integer> bins = null;

  @JsonProperty("stats")
  private Object stats = null;

  public JBrowseFeatureDensityStats bins(List<Integer> bins) {
    this.bins = bins;
    return this;
  }

  public JBrowseFeatureDensityStats addBinsItem(Integer binsItem) {
    if (this.bins == null) {
      this.bins = new ArrayList<Integer>();
    }
    this.bins.add(binsItem);
    return this;
  }

  /**
   * Get bins
   * @return bins
  **/
  @ApiModelProperty(value = "")

  public List<Integer> getBins() {
    return bins;
  }

  public void setBins(List<Integer> bins) {
    this.bins = bins;
  }

  public JBrowseFeatureDensityStats stats(Object stats) {
    this.stats = stats;
    return this;
  }

  /**
   * Get stats
   * @return stats
  **/
  @ApiModelProperty(value = "")

  public Object getStats() {
    return stats;
  }

  public void setStats(Object stats) {
    this.stats = stats;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JBrowseFeatureDensityStats jbrowseFeatureDensityStats = (JBrowseFeatureDensityStats) o;
    return Objects.equals(this.bins, jbrowseFeatureDensityStats.bins) &&
        Objects.equals(this.stats, jbrowseFeatureDensityStats.stats) &&
        Objects.equals(this.executionTime, jbrowseFeatureDensityStats.executionTime) &&
        Objects.equals(this.wasSuccessful, jbrowseFeatureDensityStats.wasSuccessful) &&
        Objects.equals(this.error, jbrowseFeatureDensityStats.error) &&
        Objects.equals(this.statusCode, jbrowseFeatureDensityStats.statusCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bins, stats, executionTime, wasSuccessful, error, statusCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JBrowseFeatureDensityStats {\n");
    
    sb.append("    bins: ").append(toIndentedString(bins)).append("\n");
    sb.append("    stats: ").append(toIndentedString(stats)).append("\n");
    sb.append("    executionTime: ").append(toIndentedString(executionTime)).append("\n");
    sb.append("    wasSuccessful: ").append(toIndentedString(wasSuccessful)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
