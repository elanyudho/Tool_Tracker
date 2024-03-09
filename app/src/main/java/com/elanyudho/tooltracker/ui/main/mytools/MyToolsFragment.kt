package com.elanyudho.tooltracker.ui.main.mytools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.elanyudho.core.abstraction.BaseFragmentBinding
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.tooltracker.databinding.FragmentMyToolsBinding
import com.elanyudho.tooltracker.ui.dialog.borrowtool.BorrowToolDialogFragment
import com.elanyudho.tooltracker.ui.main.mytools.adapter.MyToolAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyToolsFragment : BaseFragmentBinding<FragmentMyToolsBinding>() {

    @Inject
    lateinit var myToolsViewModel: MyToolsViewModel

    private val myToolAdapter: MyToolAdapter by lazy { MyToolAdapter() }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMyToolsBinding
        get() = { layoutInflater, viewGroup, b ->
            FragmentMyToolsBinding.inflate(
                layoutInflater,
                viewGroup,
                b
            )
        }

    override fun setupView() {
        callOnceWhenCreated {
            initViewModel()
            setupRecyclerView()
        }
    }

    private fun initViewModel() {
        myToolsViewModel.uiState.observe(viewLifecycleOwner, Observer { state ->
            handleUiState(state)
        })
        myToolsViewModel.getAllTool()
    }

    private fun setupRecyclerView() {
        binding.rvTool.apply {
            adapter = myToolAdapter
            setHasFixedSize(true)
        }

        myToolAdapter.setOnClickData {
            showBorrowToolDialog(it)
        }
    }

    private fun handleUiState(state: MyToolsViewModel.MyToolUiState) {
        when (state) {
            is MyToolsViewModel.MyToolUiState.ToolsLoaded -> {
                myToolAdapter.submitList(state.data)
            }
            is MyToolsViewModel.MyToolUiState.Loading -> {
                // Handle loading state if needed

            }
            // Add other UI states if needed
        }
    }

    private fun showBorrowToolDialog(tool: Tool) {
        val dialog = BorrowToolDialogFragment().newInstance(tool)
        dialog.show(
            childFragmentManager,
            BorrowToolDialogFragment::class.java.simpleName
        )
    }

}