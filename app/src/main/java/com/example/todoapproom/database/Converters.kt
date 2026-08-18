package com.example.todoapproom.database

import androidx.room.TypeConverter
import java.util.Date

// Room can only store primitives, so Date is saved as a Long (millis)
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
