package com.elanyudho.tooltracker.ui.main.mytools

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.usecase.GetAllToolUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyToolsViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getAllToolUseCase: GetAllToolUseCase,
) : BaseViewModel<MyToolsViewModel.MyToolUiState>() {

    sealed class MyToolUiState {
        object Loading: MyToolUiState()
        data class ToolsLoaded(val data: List<Tool>) : MyToolUiState()
    }

    fun getAllTool() {
        viewModelScope.launch(dispatcherProvider.io) {
            getAllToolUseCase.getAllTool()
                .onStart {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = MyToolUiState.Loading
                    }
                }
                .collect { data ->
                    withContext(dispatcherProvider.main) {
                        _uiState.value = MyToolUiState.ToolsLoaded(data)
                    }
                }
        }
    }
}

