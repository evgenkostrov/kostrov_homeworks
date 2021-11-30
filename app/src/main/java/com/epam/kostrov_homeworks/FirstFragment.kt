package com.epam.kostrov_homeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.epam.kostrov_homeworks.databinding.FragmentFirstBinding
import kotlin.math.pow

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        with(binding) {
            buttonFirst.setOnClickListener {
                val weight = binding.textInputEditTextWeight.text.toString().toDoubleOrNull()
                val height = binding.textInputEditTextHeight.text.toString().toDoubleOrNull()
                val bmi = weight?.let {
                    height?.let {
                        Abstract.calculateAbstract(height, weight, ::calculateBMI)
                    }
                }
                if (weight == null || height == null) {
                    Toast.makeText(requireContext(), "Sorry, fill empty field", Toast.LENGTH_SHORT)
                        .show()
                    kotlin.run {textviewFirst.text = "Unmeasured"}
                } else {
                    textviewFirst.text = "Your BMI: ${bmi.toString().take(5)}"
                }
                if (bmi != null) textviewSecond.apply { text = Description(bmi).description}
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

    companion object Abstract {
        fun calculateAbstract(x: Double, y: Double, operation: (Double, Double) -> Double): Double {
            return operation(x, y)
        }

        private const val powerForCalculate = 2.0
        fun calculateBMI(height: Double, weight: Double) = weight / (height.pow(powerForCalculate))

    }
}