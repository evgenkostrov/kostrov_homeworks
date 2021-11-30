package com.epam.kostrov_homeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        var count = 0
        val listPartFirst = ListPartFirst().list
        val listPartSecond = resources.getStringArray(R.array.second_part)

        binding.buttonHourglass.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.rotate_center)
            binding.imageViewHourglass.startAnimation(anim)
        }

        val timer = object : CountDownTimer(1500, 1500) {
            override fun onTick(millisUntilFinished: Long) {
                val anim = AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate_center)
                binding.imageViewHourglass.startAnimation(anim)
                binding.buttonPlay.isClickable = false
            }

            override fun onFinish() {
                with(binding) {
                    buttonCounter.text = "$count"
                    textViewResult.apply {
                        text =
                            "${listPartFirst[randomZeroNine()]}\n${listPartSecond[randomZeroNine()].lowercase()}"
                    }
                    buttonPlay.isClickable = true
                }
            }
        }

        binding.buttonPlay.setOnClickListener {
            count++
            timer.start()
        }
    }

    private fun randomZeroNine(): Int {
        return (0..9).random()
    }
}