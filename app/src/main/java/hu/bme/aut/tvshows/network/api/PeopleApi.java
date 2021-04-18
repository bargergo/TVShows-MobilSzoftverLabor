package hu.bme.aut.tvshows.network.api;//retrofit2

import java.util.List;

import hu.bme.aut.tvshows.model.Person;
import hu.bme.aut.tvshows.model.PersonData;
import hu.bme.aut.tvshows.model.PersonSearchResult;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PeopleApi {
  /**
   * Delete person
   * 
   * @param personId  (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("people/{personId}")
  Call<Void> deletePeoplePersonId(
            @retrofit2.http.Path("personId") Integer personId            
  );

  /**
   * Get person by ID
   * Retrieve all primary information for a given person.
   * @param personId  (required)
   * @return Call&lt;Person&gt;
   */
  @GET("people/{personId}")
  Call<Person> getPeoplePersonId(
            @retrofit2.http.Path("personId") Integer personId            
  );

  /**
   * Search people
   * 
   * @param q search text (optional)
   * @return Call&lt;List&lt;PersonSearchResult&gt;&gt;
   */
  @GET("search/people")
  Call<List<PersonSearchResult>> getSearchPeople(
        @retrofit2.http.Query("q") String q                
  );

  /**
   * Create person
   * 
   * @param body  (optional)
   * @return Call&lt;Person&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("people")
  Call<Person> postPeople(
                    @retrofit2.http.Body PersonData body    
  );

  /**
   * Update person
   * 
   * @param personId  (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("people/{personId}")
  Call<Void> putPeoplePersonId(
            @retrofit2.http.Path("personId") Integer personId            ,                 @retrofit2.http.Body PersonData body    
  );

}
