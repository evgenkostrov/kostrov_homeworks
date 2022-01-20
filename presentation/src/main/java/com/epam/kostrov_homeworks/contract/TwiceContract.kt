package com.epam.kostrov_homeworks.contract

import com.epam.domain.model.Battery

interface TwiceContract {

    interface IView{
        fun getAllBattery(item: Battery)

    }

    interface IPresenter{
        fun addBattery()
    }
}