package com.elanyudho.tooltracker.ui.detailfriend

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.usecase.GetDetailFriendUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailFriendViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getDetailFriendUseCase: GetDetailFriendUseCase,
) : BaseViewModel<DetailFriendViewModel.DetailFriendUiState>() {

    sealed class DetailFriendUiState {
        object Loading: DetailFriendUiState()
        data class FriendLoaded(val data: Friend) : DetailFriendUiState()
    }

    fun getDetailFriend(id: Int) {
        viewModelScope.launch(dispatcherProvider.io) {
            getDetailFriendUseCase.getDetailFriend(id)
                .onStart {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = DetailFriendUiState.Loading
                    }
                }
                .collect { data ->
                    withContext(dispatcherProvider.main) {
                        _uiState.value = DetailFriendUiState.FriendLoaded(data)
                    }
                }
        }
    }
}