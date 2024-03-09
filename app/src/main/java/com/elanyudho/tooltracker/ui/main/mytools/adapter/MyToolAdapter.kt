package com.elanyudho.tooltracker.ui.main.mytools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elanyudho.core.abstraction.BaseViewHolder
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.util.extension.glide
import com.elanyudho.tooltracker.R
import com.elanyudho.tooltracker.databinding.ItemToolBinding
import com.elanyudho.tooltracker.ui.main.myfriends.adapter.MyFriendAdapter

class MyToolAdapter: RecyclerView.Adapter<MyToolAdapter.Holder>() {

    private var listData = ArrayList<Tool>()
    private var onClick: ((data: Tool) -> Unit?)? = null

    fun submitList(newList: List<Tool>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyToolAdapter.Holder {
        return Holder(ItemToolBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: MyToolAdapter.Holder,
        position: Int
    ) {
        holder.bind(data = listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder (itemView: ItemToolBinding) :
        BaseViewHolder<Tool, ItemToolBinding>(itemView){
        override fun bind(data: Tool) {
            with(binding) {
                tvName.text = data.name
                tvTotalTool.text =  root.resources.getString(R.string.total_tools, data.totalTool)
                tvTotalBorrowedTool.text =  root.resources.getString(R.string.borrowed_tools, data.borrowedTool)
                imgTool.glide(itemView, data.icTool?: R.drawable.ic_launcher_background)

                root.setOnClickListener {
                    onClick?.invoke(data)
                }
            }
        }
    }

    fun setOnClickData(onClick: (data: Tool) -> Unit) {
        this.onClick = onClick
    }


}