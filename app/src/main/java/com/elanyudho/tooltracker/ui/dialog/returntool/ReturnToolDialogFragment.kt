package com.elanyudho.tooltracker.ui.dialog.returntool

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.elanyudho.core.abstraction.BaseDialogBinding
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.ToolBorrowed
import com.elanyudho.core.util.extension.glide
import com.elanyudho.tooltracker.R
import com.elanyudho.tooltracker.databinding.FragmentReturnToolDialogBinding
import com.elanyudho.tooltracker.ui.dialog.borrowtool.BorrowToolViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReturnToolDialogFragment : BaseDialogBinding<FragmentReturnToolDialogBinding>(false, 750) {

    private lateinit var tool: ToolBorrowed

    private lateinit var friend: Friend


    @Inject
    lateinit var returnToolViewModel: ReturnToolViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentReturnToolDialogBinding
        get() = { layoutInflater, viewGroup, b ->
            FragmentReturnToolDialogBinding.inflate(
                layoutInflater,
                viewGroup,
                b
            )
        }


    override fun setupView() {
        setDataView()
        setAction()
    }

    private fun setDataView() {
        arguments?.let {
            tool = it.getParcelable("tool")!!
            friend = it.getParcelable("friend")!!
        }

        with(binding) {
            imgTool.glide(requireActivity(), tool.icTool ?: R.drawable.ic_launcher_background)
            tvName.text = tool.tool
            edtToolBorrow.setText(tool.quantity.toString())
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
                val currBorrow = edtToolBorrow.text.toString().toInt()
                val currReturn = edtToolReturn.text.toString().toInt()

                //input old data to the new collection
                for (i in friend.toolBorrowed) {
                    currToolBorrowed.add(i)
                }

                //to check all item is returned or just several item
                if (currReturn > 0 && currBorrow > 0) {
                    //check if there is same id, we can just update the quantity value
                    currToolBorrowed.forEachIndexed { index, value ->
                        if (value.id == tool.id) {
                            currToolBorrowed[index] = ToolBorrowed(
                                tool.id,
                                tool.tool,
                                value.quantity - currReturn,
                                tool.icTool
                            )
                        }
                    }
                    returnToolViewModel.returnTool(
                        tool.id,
                        currReturn,
                        Friend(friend.id, friend.name, currToolBorrowed)
                    )
                //if all returned, we can remove the item. so the borrow item will gone
                } else {
                    for (i in currToolBorrowed) {
                        if (i.id == tool.id) {
                            currToolBorrowed.remove(i)
                            break
                        }
                    }

                    returnToolViewModel.returnTool(
                        tool.id,
                        currReturn,
                        Friend(friend.id, friend.name, currToolBorrowed)
                    )
                }

                dismiss()
            }
        }
    }

    private fun setBtnMinusLogic() {
        with(binding) {
            var currReturnVal = edtToolReturn.text.toString().toInt()
            var currBorrowVal = edtToolBorrow.text.toString().toInt()

            if (currReturnVal > 0 && currBorrowVal <= tool.quantity) {
                currReturnVal = edtToolReturn.text.toString().toInt() - 1
                currBorrowVal = edtToolBorrow.text.toString().toInt() + 1
                edtToolReturn.setText(currReturnVal.toString())
                edtToolBorrow.setText(currBorrowVal.toString())
            }

        }
    }

    private fun setBtnPlusLogic() {
        with(binding) {
            var currReturnVal = edtToolReturn.text.toString().toInt()
            var currBorrowVal = edtToolBorrow.text.toString().toInt()

            if (currBorrowVal > 0) {
                currReturnVal = edtToolReturn.text.toString().toInt() + 1
                currBorrowVal = edtToolBorrow.text.toString().toInt() - 1
                edtToolReturn.setText(currReturnVal.toString())
                edtToolBorrow.setText(currBorrowVal.toString())
            }
        }
    }

    fun newInstance(friend: Friend, tool: ToolBorrowed): ReturnToolDialogFragment {
        val args = Bundle()
        args.putParcelable("tool", tool)
        args.putParcelable("friend", friend)
        val fragment = ReturnToolDialogFragment()
        fragment.arguments = args
        return fragment
    }

}