package hu.bme.aut.tvshows.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "season")
data class Season (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var number: Int,
    var showId: Long
)