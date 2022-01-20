package com.epam.kostrov_homeworks.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.epam.kostrov_homeworks.R
import com.epam.kostrov_homeworks.adapter.GridAdapter
import com.epam.kostrov_homeworks.databinding.ActivityMain2Binding
import com.epam.domain.model.Battery
import com.epam.kostrov_homeworks.contract.TwiceContract
import com.epam.kostrov_homeworks.presenter.TwicePresenter

class TwiceActivity : AppCompatActivity(), TwiceContract.IView {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var gridAdapter: GridAdapter
    private lateinit var presenter: TwicePresenter
    private var beginIndex = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = TwicePresenter(this)
        gridAdapter = GridAdapter()
        binding.imageAdd.setOnClickListener {
            presenter.addBattery()

        }
    }


    override fun getAllBattery(item: Battery) {
        gridAdapter.addItem(item)
        binding.apply {
            val spanCount = 2
            rv2.layoutManager = GridLayoutManager(this@TwiceActivity, spanCount)
            rv2.adapter = gridAdapter

            beginIndex++
            rv2.smoothScrollToPosition(beginIndex - 1)
        }
    }
}



