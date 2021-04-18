package hu.bme.aut.tvshows.data

import androidx.room.*

@Dao
interface SeasonDAO {
    @Query("SELECT * FROM season")
    suspend fun getSeasons(): List<Season>

    @Query("""SELECT * FROM season WHERE showId=:showId""")
    suspend fun getSeasonsForSeries(showId: Int): List<Season>

    @Insert
    suspend fun insertSeason(vararg seasons: Season)

    @Delete
    suspend fun deleteSeason(season: Season)

    @Query("DELETE FROM season")
    suspend fun deleteAllSeasons()
}