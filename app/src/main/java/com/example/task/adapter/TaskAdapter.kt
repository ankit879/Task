package com.example.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ankittask.model.DataModel
import com.example.task.databinding.ItemHolderBinding

class TaskAdapter(val context: Context, var list: ArrayList<DataModel.DataModelItem>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHolderBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHolderBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvRepositoryname.text = list[position].name ?: "N/A"
            tvProgramminglanguage.text = list[position].language ?: "N/A"
            tvNumberStars.text = list[position].stargazers_count?.toString() ?: "N/A"
            tvNumberForks.text = list[position].forks?.toString() ?: "N/A"
            tvDescription.text = list[position].description ?: "N/A"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(newItems: List<DataModel.DataModelItem>) {
        val startPosition = list.size
        list.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }
}