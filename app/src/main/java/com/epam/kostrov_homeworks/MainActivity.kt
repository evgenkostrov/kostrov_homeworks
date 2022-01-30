package com.epam.kostrov_homeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding
import com.epam.kostrov_homeworks.repository.SharedPreferenceRepository
import com.epam.kostrov_homeworks.repository.TextTrain

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferenceRepository= SharedPreferenceRepository(this)
        binding.button.setOnClickListener { sharedPreferenceRepository.set(TextTrain(binding.textInput.text.toString()))
            binding.button2.setOnClickListener { binding.textOutput.text=
                sharedPreferenceRepository.get()?.txt ?:"empty"
            }


        }


    }
}