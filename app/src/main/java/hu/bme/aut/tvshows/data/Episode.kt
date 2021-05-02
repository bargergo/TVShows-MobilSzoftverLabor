package hu.bme.aut.tvshows.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
data class Episode (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var seasonId: Long,
    var number: Int,
    var name: String,
    var season: Int,
    var summary: String?
)