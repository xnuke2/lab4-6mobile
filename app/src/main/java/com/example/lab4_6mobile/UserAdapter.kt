package com.example.lab4_6mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_6mobile.Entities.Division
import com.example.lab4_6mobile.databinding.ItemPersonBinding
import com.example.lab4_6mobile.Entities.User

class UserAdapter(private val context: Context, private var userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserItemViewHolder>() {

    // Обновляем список и уведомляем адаптер
    fun updateData(newList: List<User>) {
        userList = newList
        notifyDataSetChanged()
    }
    var divisionData:List<Division> = emptyList()
        set(newValue) {
            field = newValue
        }

    class UserItemViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userItem: User) {
            binding.nameTextView.text = "${userItem.lastName} ${userItem.firstName}"
            binding.companyTextView.text = userItem.email
        }
    }

    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding = ItemPersonBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(userList[position])
    }
}