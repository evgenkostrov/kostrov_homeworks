package com.epam.kostrov_homeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.epam.kostrov_homeworks.databinding.FragmentYBinding


class YFragment : Fragment() {

    private var _binding: FragmentYBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYBinding.inflate(inflater, container, false)

        requireActivity().window.setBackgroundDrawableResource(R.color.red)
        binding.root.setOnClickListener {
            val trans = parentFragmentManager.beginTransaction()
            trans.setReorderingAllowed(true)
            trans.addToBackStack("generationY")
            trans.commit()
        }

        val callback=object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}