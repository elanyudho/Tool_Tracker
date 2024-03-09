package com.elanyudho.core.domain.usecase

import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.domain.repository.ToolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailFriendUseCase @Inject constructor(private val repo: ToolRepository) {
    suspend fun getDetailFriend(id: Int): Flow<Friend> {
        return repo.getDetailFriend(id)
    }
}