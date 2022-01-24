package com.epam.kostrov_homeworks.detail_fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.epam.kostrov_homeworks.R
import com.epam.kostrov_homeworks.databinding.FragmentDetailBinding
import com.epam.kostrov_homeworks.master_fragment.MasterSongsFragment.Companion.ARGS_KEY

class DetailSongFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding : FragmentDetailBinding

    private val viewModel : DetailSongViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        getArgument()
        initObservers()
    }

    private fun getArgument() {
        val id = requireArguments().getString(ARGS_KEY)
        viewModel.onArgumentsReceived(id)
    }

    private fun initObservers() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            binding.title.text = title
        }

        viewModel.performer.observe(viewLifecycleOwner) { performer ->
            binding.singer.text = performer
        }

        viewModel.text.observe(viewLifecycleOwner) { text ->
            binding.text.text = text
        }
    }
}