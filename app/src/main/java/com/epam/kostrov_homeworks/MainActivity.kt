package com.epam.kostrov_homeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {setContentView(it.root)  }

        var count = 0
        val listPartFirst = resources.getStringArray(R.array.first_part)
        val listPartSecond = resources.getStringArray(R.array.second_part)

//        val resultList = mutableListOf<String>()
//        resultList.add("0")
//        resultList.add("${(0..9).random()}  ${listPartFirst[(0..9).random()]}  \n ${listPartSecond[(0..9).random()].lowercase()}")
//        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, resultList)
//        binding.autoCompleteTextView.setAdapter(arrayAdapter)
//        binding.textViewHint.setCompoundDrawablesWithIntrinsicBounds()

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
                    textViewResult.text =
                        "${listPartFirst[(0..9).random()]}\n${listPartSecond[(0..9).random()].lowercase()}"
                    buttonPlay.isClickable = true
                }
            }
        }

        binding.buttonPlay.setOnClickListener {
            count++
            timer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}