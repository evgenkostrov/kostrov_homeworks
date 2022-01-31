package com.epam.kostrov_homeworks.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TextDao {
    @Query("SELECT * FROM text_train ORDER BY ID DESC LIMIT 1")
    fun get(): TextTrainEntity

    @Insert
   fun set(textTrainEntity: TextTrainEntity)
}