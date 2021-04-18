package hu.bme.aut.tvshows.network.api;//retrofit2

import java.util.List;

import hu.bme.aut.tvshows.model.Episode;
import hu.bme.aut.tvshows.model.EpisodeData;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface EpisodesApi {
  /**
   * Delete episode
   * 
   * @param episodeId The ID of the episode (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("episodes/{episodeId}")
  Call<Void> deleteEpisodesEpisodeId(
            @retrofit2.http.Path("episodeId") Integer episodeId            
  );

  /**
   * Get episode by ID
   * Retrieve all primary information for a given episode.
   * @param episodeId The ID of the episode (required)
   * @return Call&lt;Episode&gt;
   */
  @GET("episodes/{episodeId}")
  Call<Episode> getEpisodesEpisodeId(
            @retrofit2.http.Path("episodeId") Integer episodeId            
  );

  /**
   * Get episodes for season
   * A list of episodes in this season. Specials are always included in this list. 
   * @param seasonId The ID of the season (required)
   * @return Call&lt;List&lt;Episode&gt;&gt;
   */
  @GET("seasons/{seasonId}/episodes")
  Call<List<Episode>> getEpisodesForSeason(
            @retrofit2.http.Path("seasonId") Integer seasonId            
  );

  /**
   * Create episode
   * 
   * @param seasonId The ID of the season (required)
   * @param body  (optional)
   * @return Call&lt;Episode&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("seasons/{seasonId}/episodes")
  Call<Episode> postSeasonsSeasonIdEpisodes(
            @retrofit2.http.Path("seasonId") Integer seasonId            ,                 @retrofit2.http.Body EpisodeData body    
  );

  /**
   * Update episode
   * 
   * @param episodeId The ID of the episode (required)
   * @param body  (optional)
   * @return Call&lt;Episode&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("episodes/{episodeId}")
  Call<Episode> putEpisodesEpisodeId(
            @retrofit2.http.Path("episodeId") Integer episodeId            ,                 @retrofit2.http.Body EpisodeData body    
  );

}
