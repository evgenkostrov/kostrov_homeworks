package com.epam.kostrov_homeworks.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.epam.domain.model.Song
import com.epam.kostrov_homeworks.R
import com.epam.kostrov_homeworks.databinding.FragmentDetailBinding

class DetailSongFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailSongViewModel by viewModels()
    private val songObserver = Observer<Song> { song ->
        if (song != null) {
            binding.title.text = song.title
            binding.text.text = song.text
            binding.performer.text = song.performer
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val id = arguments?.getString(ARGS_KEY, "0")
        id?.let { viewModel.getDetailSong(it) }
        viewModel.detailSongs.observe(viewLifecycleOwner, songObserver)

        return binding.root
    }

    companion object {
        const val ARGS_KEY = "SONG_ARGS_KEY"
    }
}