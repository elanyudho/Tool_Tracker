package com.elanyudho.tooltracker.ui.detailfriend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elanyudho.core.abstraction.BaseViewHolder
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.data.local.room.entity.ToolBorrowed
import com.elanyudho.core.util.extension.glide
import com.elanyudho.tooltracker.R
import com.elanyudho.tooltracker.databinding.ItemToolBinding
import com.elanyudho.tooltracker.ui.main.myfriends.adapter.MyFriendAdapter

class BorrowedToolsAdapter: RecyclerView.Adapter<BorrowedToolsAdapter.Holder>() {

    private var listData = ArrayList<ToolBorrowed>()
    private var onClick: ((data: ToolBorrowed) -> Unit?)? = null

    fun submitList(newList: List<ToolBorrowed>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BorrowedToolsAdapter.Holder {
        return Holder(ItemToolBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: BorrowedToolsAdapter.Holder,
        position: Int
    ) {
        holder.bind(data = listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder (itemView: ItemToolBinding) :
        BaseViewHolder<ToolBorrowed, ItemToolBinding>(itemView){
        override fun bind(data: ToolBorrowed) {
            with(binding) {
                tvName.text = data.tool
                tvTotalTool.visibility = View.GONE
                tvTotalBorrowedTool.text =  root.resources.getString(R.string.borrowed_tools_friend, data.quantity)
                imgTool.glide(itemView, data.icTool?: R.drawable.ic_launcher_background)

                root.setOnClickListener {
                    onClick?.invoke(data)
                }
            }
        }
    }

    fun setOnClickData(onClick: (data: ToolBorrowed) -> Unit) {
        this.onClick = onClick
    }


}