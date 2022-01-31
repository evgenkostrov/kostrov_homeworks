package com.epam.kostrov_homeworks.domain

interface Repository {
    fun get(): TextTrain?
    fun set(textTrain: TextTrain)
}