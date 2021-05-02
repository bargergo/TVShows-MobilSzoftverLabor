package hu.bme.aut.tvshows.ui.model

fun Show.toDataModel() = hu.bme.aut.tvshows.data.Show(
    this.id,
    this.name,
    this.premier,
    this.genres,
    this.summary,
    this.imageUrl
)