package com.epam.kostrov_homeworks.data.repository

import android.content.Context
import androidx.room.Room
import com.epam.kostrov_homeworks.data.database.TextDatabase
import com.epam.kostrov_homeworks.data.database.TextTrainEntity
import com.epam.kostrov_homeworks.domain.Repository
import com.epam.kostrov_homeworks.domain.TextTrain

private fun TextTrainEntity.mapToTextTrain(): TextTrain {
    return TextTrain(
        txt = text,
    )
}

private fun TextTrain.mapToTextTrainEntity(): TextTrainEntity {
    return TextTrainEntity(
        id = 0,
        text = txt
    )
}

class RoomRepository (context: Context) : Repository {
    private val database = Room.databaseBuilder(
        context, TextDatabase::class.java, "textTrain.db"
    ).allowMainThreadQueries().build()

    private val dao = database.textDao()

    override fun get(): TextTrain =
        dao.get().mapToTextTrain()

    override fun set(textTrain: TextTrain) =
        dao.set(textTrain.mapToTextTrainEntity())

}

