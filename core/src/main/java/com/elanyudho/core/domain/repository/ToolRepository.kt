package com.elanyudho.core.domain.repository

import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import kotlinx.coroutines.flow.Flow

interface ToolRepository {
    suspend fun getAllFriend(): Flow<List<Friend>>

    suspend fun getAllTool(): Flow<List<Tool>>

    suspend fun getDetailFriend(id: Int): Flow<Friend>

    suspend fun updateFriend(friend: Friend)

    suspend fun addBorrowedToolQuantity(toolId: Int, borrowedQuantity: Int)

    suspend fun substractBorrowedToolQuantity(toolId: Int, returnedQuantity: Int)

    suspend fun updateAvailableToolQuantity(toolId: Int)
}