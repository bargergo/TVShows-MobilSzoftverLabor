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
    suspend fun deleteEpisodesEpisodeId(
        @Path("episodeId") episodeId: Int
    )

    /**
     * Get episode by ID
     * Retrieve all primary information for a given episode.
     * @param episodeId The ID of the episode (required)
     * @return Call&lt;Episode&gt;
     */
    @GET("episodes/{episodeId}")
    suspend fun getEpisodesEpisodeId(
        @Path("episodeId") episodeId: Int
    ): Episode

    /**
     * Get episodes for season
     * A list of episodes in this season. Specials are always included in this list.
     * @param seasonId The ID of the season (required)
     * @return Call&lt;List&lt;Episode&gt;&gt;
     */
    @GET("seasons/{seasonId}/episodes")
    suspend fun getEpisodesForSeason(
        @Path("seasonId") seasonId: Int
    ): List<Episode>

    /**
     * Create episode
     *
     * @param seasonId The ID of the season (required)
     * @param body  (optional)
     * @return Call&lt;Episode&gt;
     */
    @Headers("Content-Type:application/json")
    @POST("seasons/{seasonId}/episodes")
    suspend fun postSeasonsSeasonIdEpisodes(
        @Path("seasonId") seasonId: Int, @Body body: EpisodeData
    ): Episode

    /**
     * Update episode
     *
     * @param episodeId The ID of the episode (required)
     * @param body  (optional)
     * @return Call&lt;Episode&gt;
     */
    @Headers("Content-Type:application/json")
    @PUT("episodes/{episodeId}")
    suspend fun putEpisodesEpisodeId(
        @Path("episodeId") episodeId: Int, @Body body: EpisodeData
    ): Episode
}