package com.elanyudho.core.domain.usecase

import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.domain.repository.ToolRepository
import javax.inject.Inject

class ReturnToolUseCase @Inject constructor(private val repo: ToolRepository) {
    suspend fun returnTool(idTool: Int, returnedQuantity: Int, friend: Friend) {
        repo.substractBorrowedToolQuantity(idTool, returnedQuantity)
        repo.updateAvailableToolQuantity(idTool)
        repo.updateFriend(friend)
    }
}