
package hu.bme.aut.tvshows.model

data class Person (

    val id: Long,
    val url: String,
    val name: String,
    val country: Country,
    val birthday: String,
    val deathday: String? = null,
    val gender: String,
    val image: Image? = null,
    val links: Links
) {
}