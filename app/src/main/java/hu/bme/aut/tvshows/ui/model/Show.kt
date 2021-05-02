package hu.bme.aut.tvshows.ui.model

import org.threeten.bp.LocalDate

data class Show (
    val id: Long,
    var name: String,
    var premier: LocalDate?,
    var genres: String,
    var summary: String?,
    var imageUrl: String?,
    var isFavourite: Boolean
)