package com.epam.kostrov_homeworks.master_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.epam.domain.model.SongPreview
import com.epam.kostrov_homeworks.databinding.RowItemSongBinding

class SongItemAdapter (
    private val onItemClickListener: OnItemClickListener
): ListAdapter<SongPreview, SongItemAdapter.SongItemHolder>(DiffCallback()) {

    inner class SongItemHolder(
        private val binding: RowItemSongBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                binding.root.setOnClickListener {
                    val position = layoutPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val songItem = getItem(position)
                        onItemClickListener.onItemClick(songItem)
                    }
                }
            }
        }

        fun bind(songItem: SongPreview) {
            binding.title.text = songItem.title
            binding.performer.text = songItem.performer

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemHolder {
        val binding = RowItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongItemHolder(binding)
    }

    override fun onBindViewHolder(holder: SongItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemClick(songItem: SongPreview)
    }


    class DiffCallback: DiffUtil.ItemCallback<SongPreview>() {
        override fun areItemsTheSame(oldItem: SongPreview, newItem: SongPreview) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SongPreview, newItem: SongPreview) =
            oldItem == newItem
    }
}
