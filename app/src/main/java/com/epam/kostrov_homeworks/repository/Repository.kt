package com.epam.kostrov_homeworks.repository

interface Repository {
    fun get():TextTrain?
    fun set(textTrain: TextTrain)
}