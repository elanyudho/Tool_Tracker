package com.elanyudho.tooltracker.ui.dialog.borrowtool

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.usecase.BorrowToolUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class BorrowToolViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val borrowToolUseCase: BorrowToolUseCase,
) : BaseViewModel<BorrowToolViewModel.BorrowToolUiState>() {

    sealed class BorrowToolUiState {

    }

    fun borrowTool(idTool: Int, borrowedQuantity: Int, friend: Friend) {
        viewModelScope.launch (dispatcherProvider.io) {
            borrowToolUseCase.borrowTool(idTool, borrowedQuantity, friend)
        }
    }
}