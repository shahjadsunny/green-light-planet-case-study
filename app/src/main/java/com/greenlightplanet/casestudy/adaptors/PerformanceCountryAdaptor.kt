package com.greenlightplanet.casestudy.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenlightplanet.casestudy.databinding.PerformanceListViewBinding
import com.greenlightplanet.casestudy.model.SalesCountry
import com.greenlightplanet.casestudy.viewmodel.PerformanceViewModel

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
class PerformanceCountryAdaptor(private val performanceViewModel: PerformanceViewModel) : ListAdapter<SalesCountry, PerformanceCountryAdaptor.ViewHolder>(CountryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return  ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            holder.bind(getItem(position),performanceViewModel)
        }
    }


    class ViewHolder private constructor(val binding: PerformanceListViewBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: SalesCountry, performanceViewModel: PerformanceViewModel) {
            val text = HtmlCompat.fromHtml("<u>${item.country}</u> ", HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.textView.text = text

            itemView.setOnClickListener {
                performanceViewModel.salesZoneCallback.postValue(item)
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

    class CountryDiffCallback : DiffUtil.ItemCallback<SalesCountry>() {
        override fun areItemsTheSame(oldItem: SalesCountry, newItem: SalesCountry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SalesCountry, newItem: SalesCountry): Boolean {
            return oldItem == newItem
        }
    }
}



