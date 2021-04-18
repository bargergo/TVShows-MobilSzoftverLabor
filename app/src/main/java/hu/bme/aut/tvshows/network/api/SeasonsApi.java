package hu.bme.aut.tvshows.network.api;//retrofit2

import java.util.List;

import hu.bme.aut.tvshows.model.Season;
import hu.bme.aut.tvshows.model.SeasonData;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface SeasonsApi {
  /**
   * Delete season
   * 
   * @param seasonId The ID of the season (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("seasons/{seasonId}")
  Call<Void> deleteSeasonsSeasonId(
            @retrofit2.http.Path("seasonId") Integer seasonId            
  );

  /**
   * Get seasons for show
   * A complete list of seasons for the given show. Seasons are returned in ascending order and contain the full information that&#x27;s known about them. 
   * @param showId The ID of the show (required)
   * @return Call&lt;List&lt;Season&gt;&gt;
   */
  @GET("shows/{showId}/seasons")
  Call<List<Season>> getSeasonsForShow(
            @retrofit2.http.Path("showId") Integer showId            
  );

  /**
   * Get season by ID
   * 
   * @param seasonId The ID of the season (required)
   * @return Call&lt;Season&gt;
   */
  @GET("seasons/{seasonId}")
  Call<Season> getSeasonsShowId(
            @retrofit2.http.Path("seasonId") Integer seasonId            
  );

  /**
   * Create season
   * 
   * @param showId The ID of the show (required)
   * @param body  (optional)
   * @return Call&lt;Season&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("shows/{showId}/seasons")
  Call<Season> postShowsShowIdSeasons(
            @retrofit2.http.Path("showId") Integer showId            ,                 @retrofit2.http.Body SeasonData body    
  );

  /**
   * Update season
   * 
   * @param seasonId The ID of the season (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("seasons/{seasonId}")
  Call<Void> putSeasonsSeasonId(
            @retrofit2.http.Path("seasonId") Integer seasonId            ,                 @retrofit2.http.Body SeasonData body    
  );

}
