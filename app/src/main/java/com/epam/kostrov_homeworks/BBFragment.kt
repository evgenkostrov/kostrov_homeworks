package com.epam.kostrov_homeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.epam.kostrov_homeworks.databinding.FragmentBBBinding


class BBFragment : Fragment() {

    private var _binding: FragmentBBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBBBinding.inflate(inflater, container, false)

        requireActivity().window.setBackgroundDrawableResource(R.color.green)


        binding.root.setOnClickListener {
            val trans = parentFragmentManager.beginTransaction()
            trans.setReorderingAllowed(true)
            trans.addToBackStack("babyBoomer")
            trans.commit()
        }

        val callback=object:OnBackPressedCallback(true){
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