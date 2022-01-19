package com.epam.presentation

sealed class ItemViewModel {

        data class Category(val title: String) : ItemViewModel()
        data class Akb(val name: String, val image: Int) : ItemViewModel()
        data class Advertisement(val discount: String) : ItemViewModel()

}