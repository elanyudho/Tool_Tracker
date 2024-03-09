package com.elanyudho.tooltracker.ui.dialog.borrowtool

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.elanyudho.core.abstraction.BaseDialogBinding
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.data.local.room.entity.ToolBorrowed
import com.elanyudho.core.util.extension.glide
import com.elanyudho.tooltracker.R
import com.elanyudho.tooltracker.databinding.FragmentBorrowToolDialogBinding
import com.elanyudho.tooltracker.ui.dialog.friends.FriendSheetDialogFragment
import com.elanyudho.tooltracker.ui.main.mytools.MyToolsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BorrowToolDialogFragment : BaseDialogBinding<FragmentBorrowToolDialogBinding>(false, 750) {

    private lateinit var tool: Tool

    private lateinit var chFriend: Friend

    @Inject
    lateinit var borrowToolViewModel: BorrowToolViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBorrowToolDialogBinding
        get() = { layoutInflater, viewGroup, b ->
            FragmentBorrowToolDialogBinding.inflate(
                layoutInflater,
                viewGroup,
                b
            )
        }

    override fun setupView() {
        setDataView()
        initViewModel()
        setAction()
    }

    private fun initViewModel() {
        borrowToolViewModel.uiState.observe(viewLifecycleOwner) {}
    }

    private fun setDataView() {
        arguments?.let {
            tool = it.getParcelable("tool")!!
        }

        with(binding) {
            imgTool.glide(requireActivity(), tool.icTool ?: R.drawable.ic_launcher_background)
            tvName.text = tool.name
            edtToolAvailale.setText(tool.availableTool.toString())
        }
    }

    private fun setAction() {
        with(binding) {
            btnMinus.setOnClickListener {
                setBtnMinusLogic()
            }

            btnPlus.setOnClickListener {
                setBtnPlusLogic()
            }

            btnOk.setOnClickListener {
                //create new collection
                val currToolBorrowed = ArrayList<ToolBorrowed>()

                if (edtFriend.text.isNotEmpty() && edtToolBorrow.text.toString().toInt() > 0) {

                    //input the old data to the new collection
                    for (i in chFriend.toolBorrowed) {
                        currToolBorrowed.add(i)
                    }

                    // if empty, just add the data to the collection
                    if (currToolBorrowed.isEmpty()) {
                        currToolBorrowed.add(
                            ToolBorrowed(
                                tool.id,
                                tool.name,
                                edtToolBorrow.text.toString().toInt(),
                                tool.icTool
                            )
                        )

                    // if not empty, check the item with the same id to replace
                    } else {
                        //isUpdated use to tracking if there is data replaced
                        var isUpdated = false
                        currToolBorrowed.forEachIndexed { index, value ->
                            if (value.id == tool.id) {
                                isUpdated = true
                                currToolBorrowed[index] = ToolBorrowed(
                                    tool.id,
                                    tool.name,
                                    edtToolBorrow.text.toString()
                                        .toInt() + chFriend.toolBorrowed[index].quantity,
                                    tool.icTool
                                )
                            }
                        }

                        //if !isUpdated just add the data to the collection
                        if (!isUpdated) {
                            currToolBorrowed.add(
                                ToolBorrowed(
                                    tool.id,
                                    tool.name,
                                    edtToolBorrow.text.toString().toInt(),
                                    tool.icTool
                                )
                            )
                        }
                    }

                    //call view model function
                    borrowToolViewModel.borrowTool(
                        tool.id,
                        edtToolBorrow.text.toString().toInt(),
                        Friend(chFriend.id, chFriend.name, currToolBorrowed)
                    )

                    dismiss()

                } else {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.please_choose_your_friend),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            edtFriend.setOnClickListener {
                showFriendsDialog()
            }
        }
    }

    private fun setBtnMinusLogic() {
        with(binding) {
            var currBorrowVal = edtToolBorrow.text.toString().toInt()
            var currAvailableVal = edtToolAvailale.text.toString().toInt()

            if (currBorrowVal > 0 && currAvailableVal <= tool.totalTool) {
                currBorrowVal = edtToolBorrow.text.toString().toInt() - 1
                currAvailableVal = edtToolAvailale.text.toString().toInt() + 1
                edtToolBorrow.setText(currBorrowVal.toString())
                edtToolAvailale.setText(currAvailableVal.toString())
            }

        }
    }

    private fun setBtnPlusLogic() {
        with(binding) {
            var currBorrowVal = edtToolBorrow.text.toString().toInt()
            var currAvailableVal = edtToolAvailale.text.toString().toInt()

            if (currAvailableVal > 0) {
                currBorrowVal = edtToolBorrow.text.toString().toInt() + 1
                currAvailableVal = edtToolAvailale.text.toString().toInt() - 1
                edtToolBorrow.setText(currBorrowVal.toString())
                edtToolAvailale.setText(currAvailableVal.toString())
            }
        }
    }

    private fun showFriendsDialog() {
        val dialog = FriendSheetDialogFragment()
        dialog.show(
            childFragmentManager,
            FriendSheetDialogFragment::class.java.simpleName
        )

        dialog.setOnChooseData {
            binding.edtFriend.setText(it.name)
            chFriend = it
            dialog.dismiss()
        }
    }

    fun newInstance(tool: Tool): BorrowToolDialogFragment {
        val args = Bundle()
        args.putParcelable("tool", tool)
        val fragment = BorrowToolDialogFragment()
        fragment.arguments = args
        return fragment
    }

}