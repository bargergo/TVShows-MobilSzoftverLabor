package hu.bme.aut.tvshows.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate


@Entity(tableName = "show")
data class Show(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var name: String,
    var premier: LocalDate?,
    var genres: String,
    var summary: String?,
    var imageUrl: String?
)