package hu.bme.aut.tvshows.network.api

import hu.bme.aut.tvshows.model.Season
import hu.bme.aut.tvshows.model.SeasonData
import retrofit2.Call
import retrofit2.http.*

//retrofit2
interface SeasonsApi {
    /**
     * Delete season
     *
     * @param seasonId The ID of the season (required)
     * @return Call&lt;Void&gt;
     */
    @DELETE("seasons/{seasonId}")
    fun deleteSeasonsSeasonId(
        @Path("seasonId") seasonId: Int
    ): Call<Void>

    /**
     * Get seasons for show
     * A complete list of seasons for the given show. Seasons are returned in ascending order and contain the full information that&#x27;s known about them.
     * @param showId The ID of the show (required)
     * @return Call&lt;List&lt;Season&gt;&gt;
     */
    @GET("shows/{showId}/seasons")
    fun getSeasonsForShow(
        @Path("showId") showId: Int
    ): Call<List<Season>>

    /**
     * Get season by ID
     *
     * @param seasonId The ID of the season (required)
     * @return Call&lt;Season&gt;
     */
    @GET("seasons/{seasonId}")
    fun getSeasonsShowId(
        @Path("seasonId") seasonId: Int
    ): Call<Season>

    /**
     * Create season
     *
     * @param showId The ID of the show (required)
     * @param body  (optional)
     * @return Call&lt;Season&gt;
     */
    @Headers("Content-Type:application/json")
    @POST("shows/{showId}/seasons")
    fun postShowsShowIdSeasons(
        @Path("showId") showId: Int, @Body body: SeasonData
    ): Call<Season>

    /**
     * Update season
     *
     * @param seasonId The ID of the season (required)
     * @param body  (optional)
     * @return Call&lt;Void&gt;
     */
    @Headers("Content-Type:application/json")
    @PUT("seasons/{seasonId}")
    fun putSeasonsSeasonId(
        @Path("seasonId") seasonId: Int, @Body body: SeasonData
    ): Call<Void>
}