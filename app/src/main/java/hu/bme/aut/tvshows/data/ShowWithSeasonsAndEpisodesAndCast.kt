package hu.bme.aut.tvshows.data

import androidx.room.Embedded
import androidx.room.Relation

data class ShowWithSeasonsAndEpisodesAndCast (
    @Embedded
    val show: Show,
    @Relation(
        entity = Season::class,
        parentColumn = "id",
        entityColumn = "showId"
    )
    val seasons: List<SeasonWithEpisodes>,
    @Relation(
        parentColumn = "id",
        entityColumn = "showId"
    )
    val cast: List<Cast>
)

