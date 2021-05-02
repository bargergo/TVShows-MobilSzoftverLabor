package hu.bme.aut.tvshows.data

import androidx.room.*

@Dao
interface ShowDAO {
    @Query("SELECT * FROM show")
    suspend fun getShows(): List<Show>

    @Insert
    suspend fun insertShow(show: Show): Long

    @Delete
    suspend fun deleteShow(show: Show)

    @Query("DELETE FROM show")
    suspend fun deleteAllShows()

    @Transaction
    @Query("SELECT * FROM show")
    fun getShowsWithSeasonsAndEpisodesAndCast(): List<ShowWithSeasonsAndEpisodesAndCast>

}