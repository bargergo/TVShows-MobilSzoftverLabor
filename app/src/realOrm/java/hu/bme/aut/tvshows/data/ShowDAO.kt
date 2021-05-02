package hu.bme.aut.tvshows.data

import androidx.room.*

@Dao
interface ShowDAO {
    @Query("SELECT * FROM show")
    suspend fun getShows(): List<Show>

    @Query("SELECT id FROM show")
    suspend fun getShowIds(): List<Long>

    @Insert
    suspend fun insertShow(show: Show): Long

    @Delete
    suspend fun deleteShow(show: Show)

    @Query("DELETE FROM show")
    suspend fun deleteAllShows()

    @Transaction
    @Query("SELECT * FROM show")
    fun getShowsWithSeasonsAndEpisodesAndCast(): List<ShowWithSeasonsAndEpisodesAndCast>

    @Transaction
    @Query("SELECT * FROM show WHERE id=:showId")
    fun getShowWithSeasonsAndEpisodesAndCast(showId: Long): ShowWithSeasonsAndEpisodesAndCast?

    @Update
    fun updateShow(show: Show);

}