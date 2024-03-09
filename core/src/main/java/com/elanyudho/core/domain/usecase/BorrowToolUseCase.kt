package com.elanyudho.core.domain.usecase

import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.domain.repository.ToolRepository
import javax.inject.Inject

class BorrowToolUseCase @Inject constructor(private val repo: ToolRepository) {
    suspend fun borrowTool(idTool: Int, borrowedQuantity: Int, friend: Friend) {
        repo.addBorrowedToolQuantity(idTool, borrowedQuantity)
        repo.updateAvailableToolQuantity(idTool)
        repo.updateFriend(friend)
    }
}