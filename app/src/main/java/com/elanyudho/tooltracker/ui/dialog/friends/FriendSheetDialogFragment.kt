package com.elanyudho.tooltracker.ui.dialog.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.elanyudho.core.abstraction.BaseBottomDialogBinding
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.tooltracker.databinding.FragmentFriendSheetDialogBinding
import com.elanyudho.tooltracker.ui.main.myfriends.MyFriendViewModel
import com.elanyudho.tooltracker.ui.main.myfriends.adapter.MyFriendAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FriendSheetDialogFragment : BaseBottomDialogBinding<FragmentFriendSheetDialogBinding>() {

    @Inject
    lateinit var myFriendViewModel: MyFriendViewModel

    private val myFriendAdapter: MyFriendAdapter by lazy { MyFriendAdapter() }

    private var onChoose: ((data: Friend) -> Unit?)? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFriendSheetDialogBinding
        get() = { layoutInflater, viewGroup, b ->
            FragmentFriendSheetDialogBinding.inflate(
                layoutInflater,
                viewGroup,
                b
            )
        }

    override fun setupView() {
        initViewModel()
        setupRecyclerView()
    }

    private fun initViewModel() {
        myFriendViewModel.uiState.observe(viewLifecycleOwner, Observer { state ->
            handleUiState(state)
        })
        myFriendViewModel.getAllFriend()
    }

    private fun setupRecyclerView() {
        binding.rvFriend.apply {
            adapter = myFriendAdapter
            setHasFixedSize(true)
        }

        myFriendAdapter.setOnClickData {
            onChoose?.invoke(it)
        }
    }

    private fun handleUiState(state: MyFriendViewModel.MyFriendUiState) {
        when (state) {
            is MyFriendViewModel.MyFriendUiState.FriendsLoaded -> {
                myFriendAdapter.submitList(state.data)
            }
            is MyFriendViewModel.MyFriendUiState.Loading -> {
                // Handle loading state if needed
            }
            // Add other UI states if needed
        }
    }

    fun setOnChooseData(onChoose: (data: Friend) -> Unit) {
        this.onChoose = onChoose
    }

}