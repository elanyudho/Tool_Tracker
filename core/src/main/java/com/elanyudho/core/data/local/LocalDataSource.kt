package com.elanyudho.core.data.local

import com.elanyudho.core.data.local.room.ToolDao
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val toolDao: ToolDao) {

    suspend fun getAllFriend(): Flow<List<Friend>> = toolDao.getAllFriends()

    suspend fun getAllTool(): Flow<List<Tool>> = toolDao.getAllTools()

    suspend fun updateFriend(friend: Friend) = toolDao.updateFriend(friend)

    suspend fun getDetailFriend(id: Int): Flow<Friend> = toolDao.getDetailFriend(id)

    suspend fun addBorrowedToolQuantity(toolId: Int, borrowedQuantity: Int) = toolDao.addBorrowedToolQuantity(toolId, borrowedQuantity)

    suspend fun substractBorrowedToolQuantity(toolId: Int, returnedQuantity: Int) = toolDao.substractBorrowedToolQuantity(toolId, returnedQuantity)

    suspend fun updateAvailableToolQuantity(toolId: Int) = toolDao.updateAvailableToolQuantity(toolId)

}