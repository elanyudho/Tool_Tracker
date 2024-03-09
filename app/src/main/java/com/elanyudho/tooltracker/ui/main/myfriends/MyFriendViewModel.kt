package com.elanyudho.tooltracker.ui.main.myfriends

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.usecase.GetAllFriendUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyFriendViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getAllFriendUseCase: GetAllFriendUseCase,
) : BaseViewModel<MyFriendViewModel.MyFriendUiState>() {

    sealed class MyFriendUiState {
        object Loading: MyFriendUiState()
        data class FriendsLoaded(val data: List<Friend>) : MyFriendUiState()
    }

    fun getAllFriend() {
        viewModelScope.launch(dispatcherProvider.io) {
            getAllFriendUseCase.getAllFriend()
                .onStart {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = MyFriendUiState.Loading
                    }
                }
                .collect { data ->
                    withContext(dispatcherProvider.main) {
                        _uiState.value = MyFriendUiState.FriendsLoaded(data)
                    }
                }
        }
    }
}

