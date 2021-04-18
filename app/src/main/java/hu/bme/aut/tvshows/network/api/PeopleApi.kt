package hu.bme.aut.tvshows.network.api

import hu.bme.aut.tvshows.model.Person
import hu.bme.aut.tvshows.model.PersonData
import hu.bme.aut.tvshows.model.PersonSearchResult
import retrofit2.Call
import retrofit2.http.*

//retrofit2
interface PeopleApi {
    /**
     * Delete person
     *
     * @param personId  (required)
     * @return Call&lt;Void&gt;
     */
    @DELETE("people/{personId}")
    fun deletePeoplePersonId(
        @Path("personId") personId: Int
    ): Call<Void>

    /**
     * Get person by ID
     * Retrieve all primary information for a given person.
     * @param personId  (required)
     * @return Call&lt;Person&gt;
     */
    @GET("people/{personId}")
    fun getPeoplePersonId(
        @Path("personId") personId: Int
    ): Call<Person>

    /**
     * Search people
     *
     * @param q search text (optional)
     * @return Call&lt;List&lt;PersonSearchResult&gt;&gt;
     */
    @GET("search/people")
    fun getSearchPeople(
        @Query("q") q: String
    ): Call<List<PersonSearchResult>>

    /**
     * Create person
     *
     * @param body  (optional)
     * @return Call&lt;Person&gt;
     */
    @Headers("Content-Type:application/json")
    @POST("people")
    fun postPeople(
        @Body body: PersonData
    ): Call<Person>

    /**
     * Update person
     *
     * @param personId  (required)
     * @param body  (optional)
     * @return Call&lt;Void&gt;
     */
    @Headers("Content-Type:application/json")
    @PUT("people/{personId}")
    fun putPeoplePersonId(
        @Path("personId") personId: Int, @Body body: PersonData
    ): Call<Void>
}