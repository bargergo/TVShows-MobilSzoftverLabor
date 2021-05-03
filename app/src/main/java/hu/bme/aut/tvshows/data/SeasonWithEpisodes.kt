package hu.bme.aut.tvshows.data

import androidx.room.Embedded
import androidx.room.Relation

data class SeasonWithEpisodes (
    @Embedded
    val season: Season,
    @Relation(
            parentColumn = "id",
            entityColumn = "seasonId"
    )
    val episodes: List<Episode>
)