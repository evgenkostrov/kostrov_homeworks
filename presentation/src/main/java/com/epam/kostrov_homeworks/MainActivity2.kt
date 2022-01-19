package com.epam.kostrov_homeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.epam.kostrov_homeworks.databinding.ActivityMain2Binding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var gridAdapter: GridAdapter
    private var beginIndex = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.apply {

            val spanCount = 2
            gridAdapter = GridAdapter()
            gridAdapter.setData(getBatteryList())
            rv2.layoutManager = GridLayoutManager(this@MainActivity2, spanCount)
            rv2.adapter = gridAdapter

            imageAdd.setOnClickListener {
                gridAdapter.addItem(
                    Battery(
                        TITLE[(0..9).shuffled().last()],
                        IMAGE[(0..9).shuffled().last()]
                    )
                )
                beginIndex++
                rv2.smoothScrollToPosition(beginIndex - 1)
            }
        }
    }

    private fun getBatteryList(): List<Battery> {
        return (0..9).map {
            Battery(
                TITLE[(0..9).shuffled().last()],
                IMAGE[(0..9).shuffled().last()]
            )
        }
    }

    companion object {
        private val TITLE = mutableListOf<String>(
            "TUBOR AQUATECH",
            "TUBOR SYNERGY",
            "TUBOR STANDART",
            "TUBOR TRUCK",
            "TUBOR EFB",
            "TUBOR ASIA",
            "TUBOR GEL",
            "TITAN ARCTIC",
            "ARCTIC ASIA",
            "VAIPER"
        )

        private val IMAGE = mutableListOf<Int>(
            R.drawable.t1,
            R.drawable.t2,
            R.drawable.t3,
            R.drawable.t4,
            R.drawable.t5,
            R.drawable.t6,
            R.drawable.t7,
            R.drawable.t8,
            R.drawable.t9,
            R.drawable.t10
        )
    }
}


