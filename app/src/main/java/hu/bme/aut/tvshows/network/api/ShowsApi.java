package hu.bme.aut.tvshows.network.api;//retrofit2

import java.util.List;

import hu.bme.aut.tvshows.model.ShowData;
import hu.bme.aut.tvshows.model.ShowDetails;
import hu.bme.aut.tvshows.model.ShowSearchResult;
import hu.bme.aut.tvshows.model.ShowSummary;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ShowsApi {
  /**
   * Delete show
   * 
   * @param showId The ID of the the TV Show (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("shows/{showId}")
  Call<Void> deleteShowsShowId(
            @retrofit2.http.Path("showId") Integer showId            
  );

  /**
   * Search for TV Shows
   * Search through all the shows in our database by the show&#x27;s name. A fuzzy algorithm is used (with a fuzziness value of 2), meaning that shows will be found even if your query contains small typos. Results are returned in order of relevancy (best matches on top) and contain each show&#x27;s full information. 
   * @param q search text (optional)
   * @return Call&lt;List&lt;ShowSearchResult&gt;&gt;
   */
  @GET("search/shows")
  Call<List<ShowSearchResult>> getSearchShows(
        @retrofit2.http.Query("q") String q                
  );

  /**
   * Show Lookup
   * If you already know a show&#x27;s tvrage, thetvdb or IMDB ID, you can use this endpoint to find this exact show on TVmaze. If the given ID can be matched, a HTTP 302 redirect to the show&#x27;s URL will be returned. Otherwise, a HTTP 404 is sent. 
   * @param showId The ID of the the TV Show (required)
   * @param embed1 seasons, cast, episodes etc. (optional)
   * @param embed2 seasons, cast, episodes etc. (optional)
   * @return Call&lt;ShowDetails&gt;
   */
  @GET("shows/{showId}")
  Call<ShowDetails> getShowsWithSeasonsAndCast(
            @retrofit2.http.Path("showId") Integer showId            ,     @retrofit2.http.Query("embed[1]") String embed1                ,     @retrofit2.http.Query("embed[2]") String embed2                
  );

  /**
   * Create show
   * 
   * @param body  (optional)
   * @return Call&lt;ShowSummary&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("shows")
  Call<ShowSummary> postShows(
                    @retrofit2.http.Body ShowData body    
  );

  /**
   * Update show
   * 
   * @param showId The ID of the the TV Show (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("shows/{showId}")
  Call<Void> putShowsShowId(
            @retrofit2.http.Path("showId") Integer showId            ,                 @retrofit2.http.Body ShowData body    
  );

}
