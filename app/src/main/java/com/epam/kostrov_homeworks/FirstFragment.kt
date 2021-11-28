package com.epam.kostrov_homeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.epam.kostrov_homeworks.databinding.FragmentFirstBinding
import kotlin.math.pow

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        with(binding) {
            buttonFirst.setOnClickListener {
                val weight = binding.textInputEditTextWeight.text.toString().toDoubleOrNull()
                val height= binding.textInputEditTextHeight.text.toString().toDoubleOrNull()
                val bmi= weight?.let { height?.let {  Abstract.calculateAbstract(height, weight,::calculateBMI)
                }}
                if (weight==null||height==null){
                    Toast.makeText(requireContext(),"Sorry, fill empty field",Toast.LENGTH_SHORT).show()
                    textviewFirst.text = ""
                }else{
                    textviewFirst.text = "Your BMI: ${bmi.toString()}"
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object Abstract{
        fun calculateAbstract (x:Double, y:Double, operation: (Double,Double)->Double):Double {
            return operation(x,y)
        }
        fun calculateBMI (height:Double, weight:Double) = weight/(height.pow(2.0))

    }
}