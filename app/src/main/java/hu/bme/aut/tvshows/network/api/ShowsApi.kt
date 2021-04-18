package hu.bme.aut.tvshows.network.api

import hu.bme.aut.tvshows.model.ShowData
import hu.bme.aut.tvshows.model.ShowDetails
import hu.bme.aut.tvshows.model.ShowSearchResult
import hu.bme.aut.tvshows.model.ShowSummary
import retrofit2.Call
import retrofit2.http.*

//retrofit2
interface ShowsApi {
    /**
     * Delete show
     *
     * @param showId The ID of the the TV Show (required)
     * @return Call&lt;Void&gt;
     */
    @DELETE("shows/{showId}")
    suspend fun deleteShowsShowId(
        @Path("showId") showId: Int
    )

    /**
     * Search for TV Shows
     * Search through all the shows in our database by the show&#x27;s name. A fuzzy algorithm is used (with a fuzziness value of 2), meaning that shows will be found even if your query contains small typos. Results are returned in order of relevancy (best matches on top) and contain each show&#x27;s full information.
     * @param q search text (optional)
     * @return Call&lt;List&lt;ShowSearchResult&gt;&gt;
     */
    @GET("search/shows")
    suspend fun getSearchShows(
        @Query("q") q: String
    ): List<ShowSearchResult>

    /**
     * Show Lookup
     * If you already know a show&#x27;s tvrage, thetvdb or IMDB ID, you can use this endpoint to find this exact show on TVmaze. If the given ID can be matched, a HTTP 302 redirect to the show&#x27;s URL will be returned. Otherwise, a HTTP 404 is sent.
     * @param showId The ID of the the TV Show (required)
     * @param embed1 seasons, cast, episodes etc. (optional)
     * @param embed2 seasons, cast, episodes etc. (optional)
     * @return Call&lt;ShowDetails&gt;
     */
    @GET("shows/{showId}")
    suspend fun getShowsWithSeasonsAndCast(
        @Path("showId") showId: Int,
        @Query("embed[1]") embed1: String?,
        @Query("embed[2]") embed2: String?
    ): ShowDetails

    /**
     * Create show
     *
     * @param body  (optional)
     * @return Call&lt;ShowSummary&gt;
     */
    @Headers("Content-Type:application/json")
    @POST("shows")
    suspend fun postShows(
        @Body body: ShowData
    ): ShowSummary

    /**
     * Update show
     *
     * @param showId The ID of the the TV Show (required)
     * @param body  (optional)
     * @return Call&lt;Void&gt;
     */
    @Headers("Content-Type:application/json")
    @PUT("shows/{showId}")
    suspend fun putShowsShowId(
        @Path("showId") showId: Int, @Body body: ShowData
    )
}