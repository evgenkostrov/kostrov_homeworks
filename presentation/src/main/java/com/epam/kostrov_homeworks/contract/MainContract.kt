package com.epam.kostrov_homeworks.contract

import com.epam.domain.model.ItemViewModel

interface MainContract {
    interface IView{
        fun getAllItems(data: List<ItemViewModel>)
    }

    interface IPresenter{
        fun getAllAkb()
        fun deleteAdv(item: ItemViewModel)
    }
}