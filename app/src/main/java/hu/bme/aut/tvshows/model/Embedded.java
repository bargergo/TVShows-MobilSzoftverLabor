/*
 * TVMaze
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package hu.bme.aut.tvshows.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Embedded
 */

public class Embedded {
  @SerializedName("seasons")
  private List<Season> seasons = null;

  @SerializedName("cast")
  private List<Cast> cast = null;

  public Embedded seasons(List<Season> seasons) {
    this.seasons = seasons;
    return this;
  }

  public Embedded addSeasonsItem(Season seasonsItem) {
    if (this.seasons == null) {
      this.seasons = new ArrayList<Season>();
    }
    this.seasons.add(seasonsItem);
    return this;
  }

   /**
   * Get seasons
   * @return seasons
  **/
  @Schema(description = "")
  public List<Season> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<Season> seasons) {
    this.seasons = seasons;
  }

  public Embedded cast(List<Cast> cast) {
    this.cast = cast;
    return this;
  }

  public Embedded addCastItem(Cast castItem) {
    if (this.cast == null) {
      this.cast = new ArrayList<Cast>();
    }
    this.cast.add(castItem);
    return this;
  }

   /**
   * Get cast
   * @return cast
  **/
  @Schema(description = "")
  public List<Cast> getCast() {
    return cast;
  }

  public void setCast(List<Cast> cast) {
    this.cast = cast;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Embedded embedded = (Embedded) o;
    return Objects.equals(this.seasons, embedded.seasons) &&
        Objects.equals(this.cast, embedded.cast);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seasons, cast);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Embedded {\n");
    
    sb.append("    seasons: ").append(toIndentedString(seasons)).append("\n");
    sb.append("    cast: ").append(toIndentedString(cast)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
