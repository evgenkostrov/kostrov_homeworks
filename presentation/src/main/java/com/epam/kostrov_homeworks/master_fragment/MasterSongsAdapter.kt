package com.epam.kostrov_homeworks.master_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epam.domain.model.SongPreview
import com.epam.kostrov_homeworks.databinding.RowItemSongBinding

class MasterSongsAdapter (private val itemClickListener: (String) -> Unit) :
    RecyclerView.Adapter<MasterSongsAdapter.ViewHolder>() {

    private var songs: MutableList<SongPreview> = mutableListOf()

    inner class ViewHolder(
        private val binding: RowItemSongBinding,
        val itemClickListener: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SongPreview) {
            binding.title.text = item.title
            binding.performer.text = item.performer
            binding.root.setOnClickListener { itemClickListener(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount(): Int {
        return songs.count()
    }

    fun updateList(list: List<SongPreview>) {
    songs = list as MutableList<SongPreview>
    }





}

