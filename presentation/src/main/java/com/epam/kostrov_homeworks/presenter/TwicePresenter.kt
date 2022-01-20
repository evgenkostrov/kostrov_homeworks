package com.epam.kostrov_homeworks.presenter

import android.os.Handler
import com.epam.domain.model.ItemViewModel
import com.epam.domain.usecase.AddItemTwiceUsecase
import com.epam.domain.usecase.DeleteItemUsecase
import com.epam.domain.usecase.GetAllAkbUseCase
import com.epam.kostrov_homeworks.contract.MainContract
import com.epam.kostrov_homeworks.contract.TwiceContract
import com.epam.kostrov_homeworks.repository.RepositoryImpl

class TwicePresenter (private val view: TwiceContract.IView): TwiceContract.IPresenter {
    private val repository= RepositoryImpl()
    private val addItemTwiceUsecase= AddItemTwiceUsecase(repository)


    override fun addBattery() {
        val pause=2000L
        Handler().postDelayed({
            view.getAllBattery(addItemTwiceUsecase.invoke())
        },pause)
    }
}