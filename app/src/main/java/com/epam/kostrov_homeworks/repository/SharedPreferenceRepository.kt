package com.epam.kostrov_homeworks.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val PREFS = "txt"

class SharedPreferenceRepository(context: Context) :Repository{

    private val prefs = context.getSharedPreferences(PREFS,MODE_PRIVATE)


    override fun get(): List<TextTrain> {
        TODO("Not yet implemented")
    }

    override fun set(textTrain: TextTrain) {
        TODO("Not yet implemented")
    }
}