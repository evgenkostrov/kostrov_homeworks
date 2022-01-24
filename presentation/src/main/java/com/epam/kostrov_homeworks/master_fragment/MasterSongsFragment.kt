package com.epam.kostrov_homeworks.master_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epam.domain.model.SongPreview
import com.epam.kostrov_homeworks.R
import com.epam.kostrov_homeworks.databinding.FragmentMasterBinding

class MasterSongsFragment : Fragment(R.layout.fragment_master) {

    private lateinit var binding : FragmentMasterBinding

    private val viewModel : MasterSongsViewModel by viewModels()

    private val songAdapter = SongItemAdapter(
        object : SongItemAdapter.OnItemClickListener {
            override fun onItemClick(songItem: SongPreview) {
                val args = Bundle()
                args.putString(ARGS_KEY, songItem.id)
                findNavController().navigate(
                    R.id.action_masterSongsFragment_to_songFragment,
                    args
                )
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMasterBinding.bind(view)
        binding.recyclerView.adapter = songAdapter
        viewModel.masterSongs.observe(viewLifecycleOwner) { masterSongs ->
            songAdapter.submitList(masterSongs)
        }
    }

    companion object {
        const val ARGS_KEY = "SONG_ARGS_KEY"
    }

}