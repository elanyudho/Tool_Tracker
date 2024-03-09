package com.elanyudho.core.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tool", indices = [Index(value = [ "id" ], unique = true)] )
data class Tool(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "tool")
    val name: String = "",

    @ColumnInfo(name = "totalTool")
    val totalTool: Int = 0,

    @ColumnInfo(name = "availableTool")
    val availableTool: Int = 0,

    @ColumnInfo(name = "borrowedTool")
    val borrowedTool: Int = 0,

    @ColumnInfo(name = "icTool")
    val icTool: Int? = 0,
): Parcelable
