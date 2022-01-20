package com.epam.kostrov_homeworks.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.epam.kostrov_homeworks.R
import com.epam.kostrov_homeworks.adapter.LinearAdapter
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding
import com.epam.domain.model.ItemViewModel
import com.epam.kostrov_homeworks.contract.MainContract
import com.epam.kostrov_homeworks.presenter.MainPresenter

class MainActivity : AppCompatActivity(), LinearAdapter.ItemClickListener,MainContract.IView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearAdapter: LinearAdapter
    private lateinit var presenter: MainContract.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageViewAkb.setOnClickListener {
            startActivity(Intent(this, TwiceActivity::class.java))
        }
        presenter=MainPresenter(this)
        presenter.getAllAkb()
    }

    override fun clickOnRow(item: ItemViewModel) {
        presenter.deleteAdv(item)
    }

    override fun getAllItems(data: List<ItemViewModel>) {
        linearAdapter = LinearAdapter(this)
        linearAdapter.setData(data)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = linearAdapter
        }
    }

}