
package hu.bme.aut.tvshows.model

data class Character (

    val id: Long,
    val url: String,
    val name: String,
    val image: Image? = null,
    val links: Links
) {
}