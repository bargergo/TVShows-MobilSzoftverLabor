package hu.bme.aut.tvshows.ui.model


fun hu.bme.aut.tvshows.data.Show.toUIModel() = Show(
    this.id!!,
    this.name,
    this.premier,
    this.genres,
    this.summary,
    this.imageUrl,
    true
)