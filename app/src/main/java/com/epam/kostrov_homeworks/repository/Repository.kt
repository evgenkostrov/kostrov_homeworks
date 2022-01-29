package com.epam.kostrov_homeworks.repository

interface Repository {
    fun get():List<TextTrain>
    fun set(textTrain: TextTrain)
}