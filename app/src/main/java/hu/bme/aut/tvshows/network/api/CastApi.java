package hu.bme.aut.tvshows.network.api;//retrofit2

import java.util.List;

import hu.bme.aut.tvshows.model.Cast;
import hu.bme.aut.tvshows.model.CharacterData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CastApi {
  /**
   * Get cast for show
   * A list of main cast for a show. Each cast item is a combination of a person and a character. Items are ordered by importance, which is determined by the total number of appearances of the given character in this show. 
   * @param showId The ID of the show (required)
   * @return Call&lt;List&lt;Cast&gt;&gt;
   */
  @GET("shows/{showId}/cast")
  Call<List<Cast>> getCastForShow(
            @retrofit2.http.Path("showId") Integer showId            
  );

  /**
   * Add or Update cast
   * 
   * @param showId The ID of the show (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("shows/{showId}/cast")
  Call<Void> postShowsShowIdCast(
            @retrofit2.http.Path("showId") Integer showId            ,                 @retrofit2.http.Body List<CharacterData> body    
  );

}
