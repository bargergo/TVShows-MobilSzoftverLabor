package hu.bme.aut.tvshows.ui.model

fun Show.toDataModel() = hu.bme.aut.tvshows.data.Show(
    this.id,
    this.name,
    this.premier,
    this.genres,
    this.summary,
    this.imageUrl
)

fun ShowDetail.toDataModel() = hu.bme.aut.tvshows.data.Show(
    this.id,
    this.name,
    this.premier,
    this.genres,
    this.summary,
    this.imageUrl
)

fun Season.toDataModel() = hu.bme.aut.tvshows.data.Season(
        this.id,
        this.number,
        this.showId
)

fun Cast.toDataModel(showId: Long) = hu.bme.aut.tvshows.data.Cast(
        this.id,
        showId,
        this.imageUrl,
        this.characterName,
        this.actorName
)