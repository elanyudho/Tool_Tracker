package com.elanyudho.core.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "friend", indices = [Index(value = [ "id" ], unique = true)] )
data class Friend(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "toolBorrowed")
    val toolBorrowed: List<ToolBorrowed> = ArrayList()
    ) : Parcelable



