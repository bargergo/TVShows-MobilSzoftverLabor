package hu.bme.aut.tvshows.network.api

import hu.bme.aut.tvshows.model.Episode
import hu.bme.aut.tvshows.model.EpisodeData
import retrofit2.Call
import retrofit2.http.*

//retrofit2
interface EpisodesApi {
    /**
     * Delete episode
     *
     * @param episodeId The ID of the episode (required)
     * @return Call&lt;Void&gt;
     */
    @DELETE("episodes/{episodeId}")
    fun deleteEpisodesEpisodeId(
        @Path("episodeId") episodeId: Int
    ): Call<Void>

    /**
     * Get episode by ID
     * Retrieve all primary information for a given episode.
     * @param episodeId The ID of the episode (required)
     * @return Call&lt;Episode&gt;
     */
    @GET("episodes/{episodeId}")
    fun getEpisodesEpisodeId(
        @Path("episodeId") episodeId: Int
    ): Call<Episode>

    /**
     * Get episodes for season
     * A list of episodes in this season. Specials are always included in this list.
     * @param seasonId The ID of the season (required)
     * @return Call&lt;List&lt;Episode&gt;&gt;
     */
    @GET("seasons/{seasonId}/episodes")
    fun getEpisodesForSeason(
        @Path("seasonId") seasonId: Int
    ): Call<List<Episode>>

    /**
     * Create episode
     *
     * @param seasonId The ID of the season (required)
     * @param body  (optional)
     * @return Call&lt;Episode&gt;
     */
    @Headers("Content-Type:application/json")
    @POST("seasons/{seasonId}/episodes")
    fun postSeasonsSeasonIdEpisodes(
        @Path("seasonId") seasonId: Int, @Body body: EpisodeData
    ): Call<Episode>

    /**
     * Update episode
     *
     * @param episodeId The ID of the episode (required)
     * @param body  (optional)
     * @return Call&lt;Episode&gt;
     */
    @Headers("Content-Type:application/json")
    @PUT("episodes/{episodeId}")
    fun putEpisodesEpisodeId(
        @Path("episodeId") episodeId: Int, @Body body: EpisodeData
    ): Call<Episode>
}