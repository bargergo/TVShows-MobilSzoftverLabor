package hu.bme.aut.tvshows.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShowDAO {
    @Query("SELECT * FROM show")
    suspend fun getShows(): List<Show>

    @Insert
    suspend fun insertShow(show: Show)

    @Delete
    suspend fun deleteShow(show: Show)

    @Query("DELETE FROM show")
    suspend fun deleteAllShows()
}