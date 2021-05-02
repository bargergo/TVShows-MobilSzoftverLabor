package hu.bme.aut.tvshows.data

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromLong(epoch: Long?): LocalDate? {
        return if (epoch == null) null else LocalDate.ofEpochDay(epoch)
    }

    @TypeConverter
    fun localDateToEpoch(localDate: LocalDate?): Long? {
        return if (localDate == null) null else localDate.toEpochDay()
    }
}