package com.yusril.doaharian.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusril.doaharian.core.R
import com.yusril.doaharian.core.databinding.ListItemBinding
import com.yusril.doaharian.core.domain.model.Doa

class DoaAdapter : RecyclerView.Adapter<DoaAdapter.ListViewHolder>() {

    private var listData = ArrayList<Doa>()
    var onItemClick: ((Doa) -> Unit)? = null

    fun setData(newListData: List<Doa>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(data: Doa) {
            with(binding) {
                doaTitle.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}