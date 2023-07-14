package com.rohim.kurniawan.suitmediatest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rohim.kurniawan.suitmediatest.R
import com.rohim.kurniawan.suitmediatest.databinding.ItemUserBinding
import com.rohim.kurniawan.suitmediatest.model.DataItem

class UserAdapter(): RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    private val userList = ArrayList<DataItem>()
    private var onClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ItemViewHolder((view))
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    inner class ItemViewHolder(private val itemBinding: ItemUserBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: DataItem) {
            itemBinding.root.setOnClickListener{
                onClickCallback?.onItemClicked(data)
            }
            itemBinding.apply {
                tvFirstName.text = data.firstName
                tvLastName.text = data.lastName
                tvEmailUser.text = data.email
                Glide.with(itemView)
                    .load(data.avatar)
                    .circleCrop()
                    .into(ciUser)
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: DataItem)
    }

    fun setClickCallback(ItemClickCallback: OnItemClickCallback){
        this.onClickCallback = ItemClickCallback
    }
    fun clearUsers() {
        this.userList.clear()
        notifyDataSetChanged()
    }

    fun setList(users:ArrayList<DataItem>){
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }
}