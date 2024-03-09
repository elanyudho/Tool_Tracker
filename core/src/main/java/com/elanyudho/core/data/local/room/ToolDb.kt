package com.elanyudho.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.util.Converters

@Database(entities = [Tool::class, Friend::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ToolDb : RoomDatabase() {

    abstract fun toolDao(): ToolDao


}