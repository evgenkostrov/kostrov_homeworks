package com.epam.kostrov_homeworks.master_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.epam.domain.model.SongPreview
import com.epam.kostrov_homeworks.R
import com.epam.kostrov_homeworks.databinding.FragmentMasterBinding

class MasterSongsFragment : Fragment(R.layout.fragment_master) {

    private lateinit var binding : FragmentMasterBinding

    private val viewModel:MasterSongsViewModel by viewModels()
    private val recyclerViewAdapter = MasterSongsAdapter(::actionToDetailFragment)

    private val masterSongPreviewListObserver = Observer<List<SongPreview>> {
        val listSongPreview=it
        if (listSongPreview != null)
            recyclerViewAdapter.updateList(listSongPreview)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMasterBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerView
        recyclerView.apply { layoutManager= LinearLayoutManager(this.context,
                              LinearLayoutManager.VERTICAL, false)
                            adapter=recyclerViewAdapter}
        viewModel.masterSongs.observe(
            viewLifecycleOwner,
            masterSongPreviewListObserver
        )
        return binding.root
    }

    private fun actionToDetailFragment(id: String) {
        val bundle = Bundle()
        bundle.putString(ARGS_KEY,id)
        findNavController().navigate(R.id.action_masterSongsFragment_to_songFragment,bundle)
    }

        companion object {
        const val ARGS_KEY = "SONG_ARGS_KEY"
    }
}