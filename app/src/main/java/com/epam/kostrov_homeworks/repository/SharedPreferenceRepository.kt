package com.epam.kostrov_homeworks.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val PREFS = "txt"

class SharedPreferenceRepository(context: Context) :Repository{

    private val prefs = context.getSharedPreferences(PREFS,MODE_PRIVATE)


    override fun get(): TextTrain? {

        return prefs.getString(PREFS,"empty")?.let { TextTrain(it) }
    }

    override fun set(textTrain: TextTrain) {
        with(prefs.edit()){
            putString(PREFS,textTrain.txt)
            apply()
        }
    }
}