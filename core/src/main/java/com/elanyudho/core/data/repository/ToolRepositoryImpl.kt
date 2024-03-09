package com.elanyudho.core.data.repository

import com.elanyudho.core.data.local.LocalDataSource
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.domain.repository.ToolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToolRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : ToolRepository {

    override suspend fun getAllFriend(): Flow<List<Friend>> {
        return localDataSource.getAllFriend()
    }

    override suspend fun getAllTool(): Flow<List<Tool>> {
        return localDataSource.getAllTool()
    }

    override suspend fun getDetailFriend(id: Int): Flow<Friend> {
        return localDataSource.getDetailFriend(id)
    }

    override suspend fun updateFriend(friend: Friend) {
        localDataSource.updateFriend(friend)
    }

    override suspend fun addBorrowedToolQuantity(toolId: Int, borrowedQuantity: Int) {
        localDataSource.addBorrowedToolQuantity(toolId, borrowedQuantity)
    }

    override suspend fun substractBorrowedToolQuantity(toolId: Int, returnedQuantity: Int) {
        localDataSource.substractBorrowedToolQuantity(toolId, returnedQuantity)
    }

    override suspend fun updateAvailableToolQuantity(toolId: Int) {
        localDataSource.updateAvailableToolQuantity(toolId)
    }


}