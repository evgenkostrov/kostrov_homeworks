package com.epam.kostrov_homeworks.repository

import android.content.Context
import androidx.room.Room
import com.epam.kostrov_homeworks.data.TextDatabase
import com.epam.kostrov_homeworks.data.TextTrainEntity
import com.epam.kostrov_homeworks.domain.Repository
import com.epam.kostrov_homeworks.domain.TextTrain

class RoomRepository (context: Context) : Repository {
    private val database = Room.databaseBuilder(
        context, TextDatabase::class.java, "textTrain.db"
    ).allowMainThreadQueries().build()

    private val dao = database.textDao()

    override fun get(): TextTrain =
        dao.get().toTextTrain()

    override fun set(textTrain: TextTrain) =
        dao.set(textTrain.toTextTrainEntity())

}

private fun TextTrainEntity.toTextTrain(): TextTrain {
    return TextTrain(
        txt = text,
    )
}

private fun TextTrain.toTextTrainEntity(): TextTrainEntity {
    return TextTrainEntity(
        id = 0,
        text = txt
    )
}