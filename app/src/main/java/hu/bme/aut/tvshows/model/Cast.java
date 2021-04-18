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

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Cast
 */

public class Cast {
  @SerializedName("person")
  private Person person = null;

  @SerializedName("character")
  private Character character = null;

  @SerializedName("self")
  private Boolean self = null;

  @SerializedName("voice")
  private Boolean voice = null;

  public Cast person(Person person) {
    this.person = person;
    return this;
  }

   /**
   * Get person
   * @return person
  **/
  @Schema(required = true, description = "")
  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Cast character(Character character) {
    this.character = character;
    return this;
  }

   /**
   * Get character
   * @return character
  **/
  @Schema(required = true, description = "")
  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public Cast self(Boolean self) {
    this.self = self;
    return this;
  }

   /**
   * Get self
   * @return self
  **/
  @Schema(required = true, description = "")
  public Boolean isSelf() {
    return self;
  }

  public void setSelf(Boolean self) {
    this.self = self;
  }

  public Cast voice(Boolean voice) {
    this.voice = voice;
    return this;
  }

   /**
   * Get voice
   * @return voice
  **/
  @Schema(required = true, description = "")
  public Boolean isVoice() {
    return voice;
  }

  public void setVoice(Boolean voice) {
    this.voice = voice;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cast cast = (Cast) o;
    return Objects.equals(this.person, cast.person) &&
        Objects.equals(this.character, cast.character) &&
        Objects.equals(this.self, cast.self) &&
        Objects.equals(this.voice, cast.voice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person, character, self, voice);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cast {\n");
    
    sb.append("    person: ").append(toIndentedString(person)).append("\n");
    sb.append("    character: ").append(toIndentedString(character)).append("\n");
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    voice: ").append(toIndentedString(voice)).append("\n");
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
