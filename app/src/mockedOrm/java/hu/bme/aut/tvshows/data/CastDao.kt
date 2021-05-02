package hu.bme.aut.tvshows.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CastDao {
    @Query("SELECT * FROM `cast`")
    suspend fun getCast(): List<Cast>

    @Query("""SELECT * FROM `cast` WHERE showId=:showId""")
    suspend fun getCastForShow(showId: Long): List<Cast>

    @Insert
    suspend fun insertCast(vararg cast: Cast)

    @Delete
    suspend fun deleteCast(cast: Cast)

    @Query("DELETE FROM `cast`")
    suspend fun deleteAllCast()
}