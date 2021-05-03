package hu.bme.aut.tvshows.ui.model

import hu.bme.aut.tvshows.data.*
import hu.bme.aut.tvshows.data.Cast
import hu.bme.aut.tvshows.data.Episode
import hu.bme.aut.tvshows.util.stripHtml


fun hu.bme.aut.tvshows.data.Show.toUIModel() = Show(
    this.id!!,
    this.name,
    this.premier,
    this.genres,
    this.summary,
    this.imageUrl,
    true
)

fun Cast.toUIModel() = hu.bme.aut.tvshows.ui.model.Cast(
    this.id,
    this.characterName,
    this.actorName,
    this.imageUrl
)

fun SeasonWithEpisodes.toUIModel() = hu.bme.aut.tvshows.ui.model.Season(
    this.season.id,
    this.season.number,
    this.season.showId,
    this.episodes.size
)

fun ShowWithSeasonsAndEpisodesAndCast.toUIModel() = ShowDetail(
    this.show.id!!,
    this.show.name,
    this.show.premier,
    this.show.genres,
    this.show.summary,
    this.show.imageUrl,
    true,
    this.cast.map { it.toUIModel() },
    this.seasons.map { it.toUIModel() }
)

fun Episode.toUIModel() = hu.bme.aut.tvshows.ui.model.Episode(
    this.id!!,
    this.name,
    this.number,
    this.summary ?: "N/A",
    this.season
)