package hu.bme.aut.tvshows.ui.model

import hu.bme.aut.tvshows.model.ShowSearchResult
import hu.bme.aut.tvshows.util.stripHtml

fun ShowSearchResult.toUIModel(favouriteIds: List<Long>) = Show(
    this.show.id,
    this.show.name,
    this.show.premiered,
    this.show.genres.joinToString(", "),
    this.show.summary?.stripHtml(),
    this.show.image?.original,
    favouriteIds.contains(this.show.id)
)