package hu.bme.aut.tvshows.model

import org.threeten.bp.LocalDate

data class ShowData (

    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Long,
    val premiered: LocalDate,
    val officialSite: String?,
    val schedule: Schedule?,
    val rating: Rating?,
    val weight: Int?,
    val network: Network?,
    val webChannel: Any? = null,
    val dvdCountry: Any? = null,
    val externals: Externals?,
    val image: Image? = null,
    val summary: String
) {
}