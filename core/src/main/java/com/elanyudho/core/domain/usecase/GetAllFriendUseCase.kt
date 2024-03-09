package com.elanyudho.core.domain.usecase

import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.domain.repository.ToolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFriendUseCase @Inject constructor(private val repo: ToolRepository) {
    suspend fun getAllFriend(): Flow<List<Friend>> {
        return repo.getAllFriend()
    }
}