/**
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
package hu.bme.aut.tvshows.model

import org.threeten.bp.LocalDate

/**
 * 
 * @param id 
 * @param url 
 * @param name 
 * @param type 
 * @param language 
 * @param genres 
 * @param status 
 * @param runtime 
 * @param premiered 
 * @param officialSite 
 * @param schedule 
 * @param rating 
 * @param weight 
 * @param network 
 * @param webChannel 
 * @param dvdCountry 
 * @param externals 
 * @param image 
 * @param summary 
 * @param updated 
 * @param links 
 */
data class ShowSummary (

    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Long,
    val premiered: LocalDate,
    val officialSite: String,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Int,
    val network: Network,
    val webChannel: Any? = null,
    val dvdCountry: Any? = null,
    val externals: Externals,
    val image: Image? = null,
    val summary: String,
    val updated: Long,
    val links: Links
) {
}