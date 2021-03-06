package hu.bme.aut.tvshows.network.api

import hu.bme.aut.tvshows.model.Cast
import hu.bme.aut.tvshows.model.CastData
import retrofit2.http.*

//retrofit2
interface CastApi {
    /**
     * Get cast for show
     * A list of main cast for a show. Each cast item is a combination of a person and a character. Items are ordered by importance, which is determined by the total number of appearances of the given character in this show.
     * @param showId The ID of the show (required)
     * @return Call&lt;List&lt;Cast&gt;&gt;
     */
    @GET("shows/{showId}/cast")
    suspend fun getCastForShow(
        @Path("showId") showId: Long
    ): List<Cast>

    /**
     * Add or Update cast
     *
     * @param showId The ID of the show (required)
     * @param body  (optional)
     * @return Call&lt;Void&gt;
     */
    @Headers("Content-Type:application/json")
    @POST("shows/{showId}/cast")
    suspend fun postShowsShowIdCast(
        @Path("showId") showId: Long, @Body body: List<CastData>
    )
}