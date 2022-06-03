package com.greenlightplanet.casestudy.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenlightplanet.casestudy.databinding.AreaListViewBinding
import com.greenlightplanet.casestudy.model.SalesArea

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
class PerformanceAreaAdaptor : ListAdapter<SalesArea, PerformanceAreaAdaptor.ViewHolder>(AreaDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return  ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            holder.bind(getItem(position))
        }
    }


    class ViewHolder private constructor(val binding: AreaListViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SalesArea) {
            binding.textView.text = item.area
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AreaListViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class AreaDiffCallback : DiffUtil.ItemCallback<SalesArea>() {
        override fun areItemsTheSame(oldItem: SalesArea, newItem: SalesArea): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SalesArea, newItem: SalesArea): Boolean {
            return oldItem == newItem
        }
    }
}
