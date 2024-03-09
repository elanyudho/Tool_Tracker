package com.elanyudho.core.domain.usecase

import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.domain.repository.ToolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllToolUseCase @Inject constructor(private val repo: ToolRepository) {
    suspend fun getAllTool(): Flow<List<Tool>> {
        return repo.getAllTool()
    }
}