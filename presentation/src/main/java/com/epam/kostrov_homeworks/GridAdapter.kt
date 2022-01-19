package com.epam.kostrov_homeworks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epam.kostrov_homeworks.databinding.RowItemCardBinding

class GridAdapter : RecyclerView.Adapter<GridAdapter.CardViewHolder>() {
    private val batteries: MutableList<Battery> = mutableListOf()

    inner class CardViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = RowItemCardBinding.bind(item)

        fun bind(battery:Battery) = with(binding) {
            name.text = battery.title
            image.setImageResource(battery.image)
        }}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(batteries[position])

    }

    override fun getItemCount(): Int = batteries.size

    fun setData(data: List<Battery>) {
        batteries.apply {
            clear()
            addAll(data)
        }
    }

    fun addItem(item: Battery) {
        batteries.add(item)
        notifyDataSetChanged()
    }
}
