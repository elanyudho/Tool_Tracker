package com.elanyudho.tooltracker.ui.dialog.returntool

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.usecase.BorrowToolUseCase
import com.elanyudho.core.domain.usecase.ReturnToolUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReturnToolViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val borrowToolUseCase: ReturnToolUseCase,
) : BaseViewModel<ReturnToolViewModel.BorrowToolUiState>() {

    sealed class BorrowToolUiState {

    }

    fun returnTool(idTool: Int, returnedQuantity: Int, friend: Friend) {
        viewModelScope.launch (dispatcherProvider.io) {
            borrowToolUseCase.returnTool(idTool, returnedQuantity, friend)
        }
    }
}