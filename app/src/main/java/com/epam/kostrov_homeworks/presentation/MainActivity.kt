package com.epam.kostrov_homeworks.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.epam.kostrov_homeworks.data.repository.ExternalStorageRepository
import com.epam.kostrov_homeworks.data.repository.InternalStorageRepository
import com.epam.kostrov_homeworks.data.repository.RoomRepository
import com.epam.kostrov_homeworks.data.repository.SharedPreferenceRepository
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding
import com.epam.kostrov_homeworks.domain.TextTrain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository
    lateinit var internalStorageRepository: InternalStorageRepository
    private lateinit var externalStorageRepository: ExternalStorageRepository
    lateinit var roomRepository: RoomRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferenceRepository = SharedPreferenceRepository(this)
        internalStorageRepository = InternalStorageRepository(this)
        externalStorageRepository = ExternalStorageRepository(this)
        roomRepository = RoomRepository(this)

        binding.button.setOnClickListener {
            sharedPreferenceRepository.set(TextTrain(binding.textInput.text.toString()))
        }
        binding.button2.setOnClickListener {
            binding.textOutput.text =
                sharedPreferenceRepository.get()?.txt ?: "empty"
        }
        binding.button3.setOnClickListener {
            internalStorageRepository.set(TextTrain(binding.textInput.text.toString()))
        }
        binding.button4.setOnClickListener {
            binding.textOutput.text =
                internalStorageRepository.get().txt
        }
        binding.button5.setOnClickListener {
            externalStorageRepository.set(TextTrain(binding.textInput.text.toString()))
        }
        binding.button6.setOnClickListener {
            binding.textOutput.text =
                externalStorageRepository.get().txt
        }
        binding.button7.setOnClickListener {
            roomRepository.set(TextTrain(binding.textInput.text.toString()))
        }
        binding.button8.setOnClickListener {
            binding.textOutput.text =
                roomRepository.get().txt
        }
    }
}