package com.epam.kostrov_homeworks.presenter

import android.os.Handler
import com.epam.data.RepositoryImpl
import com.epam.domain.model.ItemViewModel
import com.epam.domain.usecase.DeleteItemUsecase
import com.epam.domain.usecase.GetAllAkbUseCase
import com.epam.kostrov_homeworks.contract.MainContract

class MainPresenter(private val view:MainContract.IView):MainContract.IPresenter {
    private val repository=RepositoryImpl()
    private val getAllAkbUseCase=GetAllAkbUseCase(repository)
    private val deleteItemUsecase=DeleteItemUsecase(repository)

    override fun getAllAkb() {
       view.getAllItems(data=getAllAkbUseCase.invoke())
    }

    override fun deleteAdv(item: ItemViewModel) {
        val pause=2000L
        Handler().postDelayed({
            view.getAllItems(data = deleteItemUsecase.invoke(item))
        },pause)
    }

}