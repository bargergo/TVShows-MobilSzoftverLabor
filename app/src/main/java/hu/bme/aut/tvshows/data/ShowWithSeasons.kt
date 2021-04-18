package hu.bme.aut.tvshows.data

import androidx.room.Embedded
import androidx.room.Relation

data class ShowWithSeasons (
    @Embedded
    val show: Show,
    @Relation(
        parentColumn = "id",
        entityColumn = "showId"
    )
    val seasons: List<Season>
)

