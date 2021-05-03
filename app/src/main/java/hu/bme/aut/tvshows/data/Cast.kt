package hu.bme.aut.tvshows.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cast")
data class Cast (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var showId: Long,
    var imageUrl: String?,
    var characterName: String,
    var actorName: String
)