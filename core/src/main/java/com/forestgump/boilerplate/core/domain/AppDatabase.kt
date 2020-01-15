package com.forestgump.boilerplate.core.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.forestgump.boilerplate.core.domain.daos.NewsLocalService
import com.forestgump.boilerplate.core.domain.entities.NewsEntity

/**
 * Room database definition
 */
@Database(
    exportSchema = true,
    version = 1,
    entities = [
        NewsEntity::class
    ]
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun news(): NewsLocalService
}
