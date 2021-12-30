package com.epam.kostrov_homeworks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LinearAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<LinearAdapter.AllViewHolder>() {

    private val modelList = mutableListOf<ItemViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {

        val layout = when (viewType) {
            ViewType.CATEGORY.ordinal -> R.layout.row_item_category
            ViewType.AKB.ordinal -> R.layout.row_item_akb
            ViewType.ADVERTISEMENT.ordinal -> R.layout.row_item_advertisement
            else -> throw IllegalArgumentException("Illegal view type")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return AllViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllViewHolder, position: Int) {
        holder.bind(modelList[position])
    }

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int {
        return when (modelList[position]) {
            is ItemViewModel.Category -> ViewType.CATEGORY.ordinal
            is ItemViewModel.Akb -> ViewType.AKB.ordinal
            is ItemViewModel.Advertisement -> ViewType.ADVERTISEMENT.ordinal
        }
    }

    fun setData(data: List<ItemViewModel>) {
        modelList.apply {
            clear()
            addAll(data)
        }
    }

    fun deleteData(item: ItemViewModel) {
        modelList.remove(item)
        notifyDataSetChanged()
    }

    inner class AllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private fun bindCategory(item: ItemViewModel.Category) {
            itemView.findViewById<TextView>(R.id.categoryTitle)?.text = item.title
        }

        private fun bindAkb(item: ItemViewModel.Akb) {
            itemView.findViewById<TextView>(R.id.text_view_name)?.text = item.name
            itemView.findViewById<ImageView>(R.id.image_view_akb)?.setImageResource(item.image)
        }

        private fun bindAdvertisement(item: ItemViewModel.Advertisement) {
            itemView.findViewById<ImageView>(R.id.advertisementClose).setOnClickListener {
                listener.clickOnRow(item)
            }
            itemView.findViewById<TextView>(R.id.advertisementDiscount)?.text = item.discount
        }

        fun bind(modelData: ItemViewModel) {
            when (modelData) {
                is ItemViewModel.Category -> bindCategory(modelData)
                is ItemViewModel.Akb -> bindAkb(modelData)
                is ItemViewModel.Advertisement -> bindAdvertisement(modelData)
            }
        }
    }

    interface ItemClickListener {
        fun clickOnRow(item: ItemViewModel)
    }
}

enum class ViewType {
    CATEGORY,
    AKB,
    ADVERTISEMENT
}