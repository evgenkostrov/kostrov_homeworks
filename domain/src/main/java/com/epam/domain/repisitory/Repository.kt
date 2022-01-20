package com.epam.domain.repisitory

import com.epam.domain.model.Battery
import com.epam.domain.model.ItemViewModel

interface Repository {

    fun getAllAKB():List <ItemViewModel>
    fun addBattery():Battery
    fun deleteAdv(item: ItemViewModel ):List <ItemViewModel>

}

