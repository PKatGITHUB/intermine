package org.intermine.webservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JBrowseConfigTrackList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-22T21:46:57.726+05:30[Asia/Kolkata]")
public class JBrowseConfigTrackList   {
  @JsonProperty("tracks")
  private Object tracks = null;

  @JsonProperty("names")
  private Object names = null;

  @JsonProperty("dataset_id")
  private String datasetId = null;

  public JBrowseConfigTrackList tracks(Object tracks) {
    this.tracks = tracks;
    return this;
  }

  /**
   * Get tracks
   * @return tracks
  **/
  @ApiModelProperty(value = "")

  public Object getTracks() {
    return tracks;
  }

  public void setTracks(Object tracks) {
    this.tracks = tracks;
  }

  public JBrowseConfigTrackList names(Object names) {
    this.names = names;
    return this;
  }

  /**
   * Get names
   * @return names
  **/
  @ApiModelProperty(value = "")

  public Object getNames() {
    return names;
  }

  public void setNames(Object names) {
    this.names = names;
  }

  public JBrowseConfigTrackList datasetId(String datasetId) {
    this.datasetId = datasetId;
    return this;
  }

  /**
   * Get datasetId
   * @return datasetId
  **/
  @ApiModelProperty(value = "")

  public String getDatasetId() {
    return datasetId;
  }

  public void setDatasetId(String datasetId) {
    this.datasetId = datasetId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JBrowseConfigTrackList jbrowseConfigTrackList = (JBrowseConfigTrackList) o;
    return Objects.equals(this.tracks, jbrowseConfigTrackList.tracks) &&
        Objects.equals(this.names, jbrowseConfigTrackList.names) &&
        Objects.equals(this.datasetId, jbrowseConfigTrackList.datasetId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tracks, names, datasetId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JBrowseConfigTrackList {\n");
    
    sb.append("    tracks: ").append(toIndentedString(tracks)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
    sb.append("    datasetId: ").append(toIndentedString(datasetId)).append("\n");
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
