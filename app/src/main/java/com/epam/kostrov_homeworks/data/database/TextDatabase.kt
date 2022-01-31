package com.epam.kostrov_homeworks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TextTrainEntity::class], version = 1)
abstract class TextDatabase : RoomDatabase() {
    abstract fun textDao(): TextDao
}