package com.elanyudho.tooltracker.ui.main.myfriends.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elanyudho.core.abstraction.BaseViewHolder
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import com.elanyudho.core.util.extension.glide
import com.elanyudho.tooltracker.R
import com.elanyudho.tooltracker.databinding.ItemFriendBinding
import com.elanyudho.tooltracker.databinding.ItemToolBinding

class MyFriendAdapter: RecyclerView.Adapter<MyFriendAdapter.Holder>() {

    private var listData = ArrayList<Friend>()
    private var onClick: ((data: Friend) -> Unit?)? = null

    fun submitList(newList: List<Friend>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        return Holder(ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        holder.bind(data = listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder (itemView: ItemFriendBinding) :
        BaseViewHolder<Friend, ItemFriendBinding>(itemView){
        override fun bind(data: Friend) {
            with(binding) {
                tvName.text = data.name

                root.setOnClickListener {
                    onClick?.invoke(data)
                }
            }
        }
    }

    fun setOnClickData(onClick: (data: Friend) -> Unit) {
        this.onClick = onClick
    }


}