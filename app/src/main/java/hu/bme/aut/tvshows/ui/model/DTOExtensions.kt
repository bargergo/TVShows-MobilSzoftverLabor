package hu.bme.aut.tvshows.ui.model

import hu.bme.aut.tvshows.model.*
import hu.bme.aut.tvshows.model.Cast
import hu.bme.aut.tvshows.model.Season
import hu.bme.aut.tvshows.util.stripHtml

fun ShowSearchResult.toUIModel(favouriteIds: List<Long>) = Show(
    this.show.id,
    this.show.name,
    this.show.premiered,
    if (this.show.genres.size > 0 ) this.show.genres.joinToString(", ") else "N/A",
    this.show.summary?.stripHtml(),
    this.show.image?.original,
    favouriteIds.contains(this.show.id)
)

fun Cast.toUIModel() = hu.bme.aut.tvshows.ui.model.Cast(
    this.character.id,
    this.character.name,
    this.person.name,
    this.character.image?.original
)

fun Season.toUIModel(showId: Long) = hu.bme.aut.tvshows.ui.model.Season(
    this.id,
    this.number,
    showId,
    this.episodeOrder
)

fun ShowDetails.toUIModel(favouriteIds: List<Long>, cast: List<Cast>, seasons: List<Season>) = ShowDetail(
    this.id,
    this.name,
    this.premiered,
    if (this.genres.size > 0 ) this.genres.joinToString(", ") else "N/A",
    this.summary?.stripHtml(),
    this.image?.original,
    favouriteIds.contains(this.id),
    cast.map { it.toUIModel() },
    seasons.map { it.toUIModel(this.id) }
)

fun Episode.toDataModel(seasonId: Long) = hu.bme.aut.tvshows.data.Episode(
    this.id,
    seasonId,
    this.number,
    this.name,
    this.season,
    this.summary?.stripHtml() ?: "N/A"
)