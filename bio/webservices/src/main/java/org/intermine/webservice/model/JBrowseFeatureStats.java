package org.intermine.webservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * JBrowseFeatureStats
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-22T21:48:44.095+05:30[Asia/Kolkata]")
@JsonPropertyOrder({ "featureDensity", "featureCount", "executionTime", "wasSuccessful", "error", "statusCode" })
public class JBrowseFeatureStats extends JSONModel {
  @JsonProperty("featureDensity")
  private Object featureDensity = null;

  @JsonProperty("featureCount")
  private Object featureCount = null;

  public JBrowseFeatureStats featureDensity(Object featureDensity) {
    this.featureDensity = featureDensity;
    return this;
  }

  /**
   * Get featureDensity
   * @return featureDensity
  **/
  @ApiModelProperty(value = "")

  @Valid
  public Object getFeatureDensity() {
    return featureDensity;
  }

  public void setFeatureDensity(Object featureDensity) {
    this.featureDensity = featureDensity;
  }

  public JBrowseFeatureStats featureCount(Object featureCount) {
    this.featureCount = featureCount;
    return this;
  }

  /**
   * Get featureCount
   * @return featureCount
  **/
  @ApiModelProperty(value = "")

  public Object getFeatureCount() {
    return featureCount;
  }

  public void setFeatureCount(Object featureCount) {
    this.featureCount = featureCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JBrowseFeatureStats jbrowseFeatureStats = (JBrowseFeatureStats) o;
    return Objects.equals(this.featureDensity, jbrowseFeatureStats.featureDensity) &&
        Objects.equals(this.featureCount, jbrowseFeatureStats.featureCount) &&
        Objects.equals(this.executionTime, jbrowseFeatureStats.executionTime) &&
        Objects.equals(this.wasSuccessful, jbrowseFeatureStats.wasSuccessful) &&
        Objects.equals(this.error, jbrowseFeatureStats.error) &&
        Objects.equals(this.statusCode, jbrowseFeatureStats.statusCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(featureDensity, featureCount, executionTime, wasSuccessful, error, statusCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JBrowseFeatureStats {\n");
    
    sb.append("    featureDensity: ").append(toIndentedString(featureDensity)).append("\n");
    sb.append("    featureCount: ").append(toIndentedString(featureCount)).append("\n");
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
