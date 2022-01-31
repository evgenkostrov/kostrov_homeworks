package com.epam.kostrov_homeworks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.epam.kostrov_homeworks.repository.TextTrain

@Dao
interface TextDao {
    @Query("SELECT * FROM text_train")
    fun get(): TextTrainEntity

    @Insert
   fun set(textTrainEntity: TextTrainEntity)
}