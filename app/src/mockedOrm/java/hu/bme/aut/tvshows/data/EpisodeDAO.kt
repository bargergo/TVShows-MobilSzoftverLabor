package hu.bme.aut.tvshows.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EpisodeDAO {
    @Query("SELECT * FROM episode")
    suspend fun getEpisodes(): List<Episode>

    @Query("""SELECT * FROM episode WHERE seasonId=:seasonId""")
    suspend fun getEpisodesForSeason(seasonId: Long): List<Episode>

    @Insert
    suspend fun insertEpisode(vararg episodes: Episode)

    @Delete
    suspend fun deleteEpisode(episode: Episode)

    @Query("DELETE FROM episode")
    suspend fun deleteAllEpisodes()

    @Query("DELETE FROM episode WHERE seasonId IN (:seasonIds)")
    suspend fun deleteEpisodesForSeasons(seasonIds: List<Long>)
}