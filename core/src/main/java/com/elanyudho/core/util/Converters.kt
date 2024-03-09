package com.elanyudho.core.util

import androidx.room.TypeConverter
import com.elanyudho.core.data.local.room.entity.ToolBorrowed
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): List<ToolBorrowed> {
        val listType = object : TypeToken<List<ToolBorrowed>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ToolBorrowed>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}