/*
 * TVMaze
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package hu.bme.aut.tvshows.network

import com.google.gson.*
import com.google.gson.internal.bind.util.ISO8601Utils
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import io.gsonfire.GsonFireBuilder
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.io.IOException
import java.sql.Date
import java.text.DateFormat
import java.text.ParseException
import java.text.ParsePosition

class JSON {
    /**
     * Get Gson.
     *
     * @return Gson
     */
    var gson: Gson
        private set
    private val dateTypeAdapter = DateTypeAdapter()
    private val sqlDateTypeAdapter = SqlDateTypeAdapter()
    private val offsetDateTimeTypeAdapter = OffsetDateTimeTypeAdapter()
    private val localDateTypeAdapter = LocalDateTypeAdapter()

    /**
     * Set Gson.
     *
     * @param gson Gson
     * @return JSON
     */
    fun setGson(gson: Gson): JSON {
        this.gson = gson
        return this
    }

    /**
     * Gson TypeAdapter for JSR310 OffsetDateTime type
     */
    class OffsetDateTimeTypeAdapter @JvmOverloads constructor(private var formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME) :
        TypeAdapter<OffsetDateTime?>() {
        fun setFormat(dateFormat: DateTimeFormatter) {
            formatter = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: OffsetDateTime?) {
            if (date == null) {
                out.nullValue()
            } else {
                out.value(formatter.format(date))
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): OffsetDateTime? {
            return when (`in`.peek()) {
                JsonToken.NULL -> {
                    `in`.nextNull()
                    null
                }
                else -> {
                    var date = `in`.nextString()
                    if (date.endsWith("+0000")) {
                        date = date.substring(0, date.length - 5) + "Z"
                    }
                    OffsetDateTime.parse(date, formatter)
                }
            }
        }
    }

    /**
     * Gson TypeAdapter for JSR310 LocalDate type
     */
    inner class LocalDateTypeAdapter @JvmOverloads constructor(private var formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE) :
        TypeAdapter<LocalDate?>() {
        fun setFormat(dateFormat: DateTimeFormatter) {
            formatter = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: LocalDate?) {
            if (date == null) {
                out.nullValue()
            } else {
                out.value(formatter.format(date))
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): LocalDate? {
            return when (`in`.peek()) {
                JsonToken.NULL -> {
                    `in`.nextNull()
                    null
                }
                else -> {
                    val date = `in`.nextString()
                    LocalDate.parse(date, formatter)
                }
            }
        }
    }

    fun setOffsetDateTimeFormat(dateFormat: DateTimeFormatter): JSON {
        offsetDateTimeTypeAdapter.setFormat(dateFormat)
        return this
    }

    fun setLocalDateFormat(dateFormat: DateTimeFormatter): JSON {
        localDateTypeAdapter.setFormat(dateFormat)
        return this
    }

    /**
     * Gson TypeAdapter for java.sql.Date type
     * If the dateFormat is null, a simple "yyyy-MM-dd" format will be used
     * (more efficient than SimpleDateFormat).
     */
    class SqlDateTypeAdapter : TypeAdapter<Date?> {
        private var dateFormat: DateFormat? = null

        constructor() {}
        constructor(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        fun setFormat(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: Date?) {
            if (date == null) {
                out.nullValue()
            } else {
                val value: String
                value = if (dateFormat != null) {
                    dateFormat!!.format(date)
                } else {
                    date.toString()
                }
                out.value(value)
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): Date? {
            return when (`in`.peek()) {
                JsonToken.NULL -> {
                    `in`.nextNull()
                    null
                }
                else -> {
                    val date = `in`.nextString()
                    try {
                        if (dateFormat != null) {
                            Date(dateFormat!!.parse(date).time)
                        } else Date(ISO8601Utils.parse(date, ParsePosition(0)).time)
                    } catch (e: ParseException) {
                        throw JsonParseException(e)
                    }
                }
            }
        }
    }

    /**
     * Gson TypeAdapter for java.util.Date type
     * If the dateFormat is null, ISO8601Utils will be used.
     */
    class DateTypeAdapter : TypeAdapter<java.util.Date?> {
        private var dateFormat: DateFormat? = null

        constructor() {}
        constructor(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        fun setFormat(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: java.util.Date?) {
            if (date == null) {
                out.nullValue()
            } else {
                val value: String
                value = if (dateFormat != null) {
                    dateFormat!!.format(date)
                } else {
                    ISO8601Utils.format(date, true)
                }
                out.value(value)
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): java.util.Date? {
            return try {
                when (`in`.peek()) {
                    JsonToken.NULL -> {
                        `in`.nextNull()
                        null
                    }
                    else -> {
                        val date = `in`.nextString()
                        try {
                            if (dateFormat != null) {
                                dateFormat!!.parse(date)
                            } else ISO8601Utils.parse(date, ParsePosition(0))
                        } catch (e: ParseException) {
                            throw JsonParseException(e)
                        }
                    }
                }
            } catch (e: IllegalArgumentException) {
                throw JsonParseException(e)
            }
        }
    }

    fun setDateFormat(dateFormat: DateFormat?): JSON {
        dateTypeAdapter.setFormat(dateFormat)
        return this
    }

    fun setSqlDateFormat(dateFormat: DateFormat?): JSON {
        sqlDateTypeAdapter.setFormat(dateFormat)
        return this
    }

    companion object {
        fun createGson(): GsonBuilder {
            val fireBuilder = GsonFireBuilder()
            return fireBuilder.createGsonBuilder()
        }

        private fun getDiscriminatorValue(
            readElement: JsonElement,
            discriminatorField: String
        ): String {
            val element = readElement.asJsonObject[discriminatorField]
                ?: throw IllegalArgumentException("missing discriminator field: <$discriminatorField>")
            return element.asString
        }

        private fun getClassByDiscriminator(
            classByDiscriminatorValue: Map<*, *>,
            discriminatorValue: String
        ): Class<*> {
            return classByDiscriminatorValue[discriminatorValue.toUpperCase()] as Class<*>?
                ?: throw IllegalArgumentException("cannot determine model class of name: <$discriminatorValue>")
        }
    }

    init {
        gson = createGson()
            .registerTypeAdapter(java.util.Date::class.java, dateTypeAdapter)
            .registerTypeAdapter(Date::class.java, sqlDateTypeAdapter)
            .registerTypeAdapter(OffsetDateTime::class.java, offsetDateTimeTypeAdapter)
            .registerTypeAdapter(LocalDate::class.java, localDateTypeAdapter)
            .create()
    }
}