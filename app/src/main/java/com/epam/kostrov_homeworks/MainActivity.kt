package com.epam.kostrov_homeworks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LinearAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearAdapter: LinearAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.imageViewAkb.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    private fun initRecyclerView() {
        linearAdapter = LinearAdapter(this)
        linearAdapter.setData(getModelList())
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = linearAdapter
        }
    }

    private fun getModelList(): List<ItemViewModel> {
        val list = mutableListOf<ItemViewModel>()
        for (a in 0..100 step 3) {
            list.addAll(
                a, listOf(
                    ItemViewModel.Category(
                        CATEGORY[(0..3).shuffled().last()]
                    )
                )
            )

            list.addAll(
                a + 1, listOf(
                    ItemViewModel.Akb(
                        TITLE[(0..9).shuffled().last()],
                        IMAGE[(0..9).shuffled().last()]
                    )
                )
            )
            list.addAll(
                a + 2, listOf(
                    ItemViewModel.Advertisement(
                        "${(10..20).random()}%"
                    )
                )
            )
        }
        return list
    }

    companion object {
        private val CATEGORY = mutableListOf<String>(
            "STARTER BATTERY",
            "INDUSTRIAL BATTERY",
            "BATTERY FOR WATER TRANSPORT",
            "BATTERY FOR START/STOP SYSTEM"
        )

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

    override fun clickOnRow(item: ItemViewModel) {
        linearAdapter.deleteData(item)
    }

}