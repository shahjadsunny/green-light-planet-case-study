package com.greenlightplanet.casestudy.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenlightplanet.casestudy.databinding.PerformanceListViewBinding
import com.greenlightplanet.casestudy.model.SalesZone
import com.greenlightplanet.casestudy.viewmodel.PerformanceViewModel

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
class PerformanceZoneAdaptor(private val performanceViewModel: PerformanceViewModel) :
    ListAdapter<SalesZone, PerformanceZoneAdaptor.ViewHolder>(TaskDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            holder.bind(getItem(position), performanceViewModel)
        }
    }


    class ViewHolder private constructor(val binding: PerformanceListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: SalesZone, performanceViewModel: PerformanceViewModel) {
            val text = HtmlCompat.fromHtml("<u>${item.zone}</u> ", HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.textView.text = text

            itemView.setOnClickListener {
                performanceViewModel.salesRegionCallback.postValue(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PerformanceListViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<SalesZone>() {
        override fun areItemsTheSame(oldItem: SalesZone, newItem: SalesZone): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SalesZone, newItem: SalesZone): Boolean {
            return oldItem == newItem
        }
    }
}


