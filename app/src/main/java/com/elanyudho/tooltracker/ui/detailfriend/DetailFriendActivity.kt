package com.elanyudho.tooltracker.ui.detailfriend

import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.ToolBorrowed
import com.elanyudho.tooltracker.R
import com.elanyudho.tooltracker.databinding.ActivityDetailFriendBinding
import com.elanyudho.tooltracker.ui.detailfriend.adapter.BorrowedToolsAdapter
import com.elanyudho.tooltracker.ui.dialog.returntool.ReturnToolDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFriendActivity : BaseActivityBinding<ActivityDetailFriendBinding>() {

    private var friendId: Int = 0
    private lateinit var friend: Friend

    @Inject
    lateinit var detailFriendViewModel: DetailFriendViewModel

    private val borrowedToolsAdapter: BorrowedToolsAdapter by lazy { BorrowedToolsAdapter() }

    override val bindingInflater: (LayoutInflater) -> ActivityDetailFriendBinding
        get() = { ActivityDetailFriendBinding.inflate(layoutInflater) }

    override fun setupView() {
        setData()
        initViewModel()
        setHeader()
        setupRecyclerView()
    }

    private fun setData() {
        intent?.apply {
            friendId = this.getIntExtra("friendId", 0)
        }
    }

    private fun initViewModel() {
        detailFriendViewModel.uiState.observe(this, Observer { state ->
            handleUiState(state)
        })
        detailFriendViewModel.getDetailFriend(friendId)
    }

    private fun handleUiState(state: DetailFriendViewModel.DetailFriendUiState) {
        when (state) {
            is DetailFriendViewModel.DetailFriendUiState.FriendLoaded -> {
                borrowedToolsAdapter.submitList(state.data.toolBorrowed)
                friend = state.data

                setView(state.data)
            }
            is DetailFriendViewModel.DetailFriendUiState.Loading -> {
                // Handle loading state if needed
            }
            // Add other UI states if needed
        }
    }

    private fun setView(data: Friend) {
        with(binding) {
            tvName.text = data.name
        }
    }

    private fun setHeader() {
        binding.headerDetail.tvHeader.text = getString(R.string.detail_friend)
        binding.headerDetail.btnBack.setOnClickListener { onBackPressed() }
    }

    private fun setupRecyclerView() {
        binding.rvTool.apply {
            adapter = borrowedToolsAdapter
            setHasFixedSize(true)
        }

        borrowedToolsAdapter.setOnClickData {
            showReturnDialog(friend, it)
        }
    }

    private fun showReturnDialog(friend: Friend, tool: ToolBorrowed) {
        val dialog = ReturnToolDialogFragment().newInstance(friend, tool)
        dialog.show(
            supportFragmentManager,
            ReturnToolDialogFragment::class.java.simpleName
        )
    }

}