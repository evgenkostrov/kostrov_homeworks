package com.epam.kostrov_homeworks.data.repository

import android.content.Context
import com.epam.kostrov_homeworks.domain.Repository
import com.epam.kostrov_homeworks.domain.TextTrain

class InternalStorageRepository (private val context: Context) : Repository {

    private val fileName = "TextTrain.txt"

    override fun get(): TextTrain {

       return context.openFileInput(fileName).bufferedReader().useLines { lines->
           lines.fold(""){some,text->"$some\n$text"}
       }.let { TextTrain(it) }

    }

    override fun set(textTrain: TextTrain) {

        val fileContents = textTrain.txt
        context.openFileOutput(fileName,Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }

    }
}