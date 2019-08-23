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
 * JBrowseFeatures
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-22T21:48:44.095+05:30[Asia/Kolkata]")
@JsonPropertyOrder({ "features", "executionTime", "wasSuccessful", "error", "statusCode" })
public class JBrowseFeatures extends JSONModel  {
  @JsonProperty("features")
  @Valid
  private List<Object> features = null;

  public JBrowseFeatures features(List<Object> features) {
    this.features = features;
    return this;
  }

  public JBrowseFeatures addFeaturesItem(Object featuresItem) {
    if (this.features == null) {
      this.features = new ArrayList<Object>();
    }
    this.features.add(featuresItem);
    return this;
  }

  /**
   * Get features
   * @return features
  **/
  @ApiModelProperty(value = "")

  public List<Object> getFeatures() {
    return features;
  }

  public void setFeatures(List<Object> features) {
    this.features = features;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JBrowseFeatures jbrowseFeatures = (JBrowseFeatures) o;
    return Objects.equals(this.features, jbrowseFeatures.features) &&
        Objects.equals(this.executionTime, jbrowseFeatures.executionTime) &&
        Objects.equals(this.wasSuccessful, jbrowseFeatures.wasSuccessful) &&
        Objects.equals(this.error, jbrowseFeatures.error) &&
        Objects.equals(this.statusCode, jbrowseFeatures.statusCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(features, executionTime, wasSuccessful, error, statusCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JBrowseFeatures {\n");
    
    sb.append("    features: ").append(toIndentedString(features)).append("\n");
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
