package com.elanyudho.tooltracker.ui.main.myfriends

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.elanyudho.core.abstraction.BaseFragmentBinding
import com.elanyudho.tooltracker.databinding.FragmentMyFriendsBinding
import com.elanyudho.tooltracker.ui.detailfriend.DetailFriendActivity
import com.elanyudho.tooltracker.ui.main.myfriends.adapter.MyFriendAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFriendsFragment : BaseFragmentBinding<FragmentMyFriendsBinding>() {

    @Inject
    lateinit var myFriendViewModel: MyFriendViewModel

    private val myFriendAdapter: MyFriendAdapter by lazy { MyFriendAdapter() }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMyFriendsBinding
        get() = { layoutInflater, viewGroup, b ->
            FragmentMyFriendsBinding.inflate(
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
            val intent = Intent(requireActivity(), DetailFriendActivity::class.java)
            intent.putExtra("friendId", it.id)
            startActivity(intent)
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


}