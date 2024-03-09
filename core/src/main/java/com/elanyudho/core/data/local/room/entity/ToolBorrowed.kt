package com.elanyudho.core.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToolBorrowed(
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "tool")
    val tool: String = "",

    @ColumnInfo(name = "quantity")
    val quantity: Int = 0,

    @ColumnInfo(name = "icTool")
    val icTool: Int? = 0,
): Parcelable
