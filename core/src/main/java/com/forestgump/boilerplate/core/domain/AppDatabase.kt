package com.forestgump.boilerplate.core.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.forestgump.boilerplate.core.domain.daos.DocumentsLocalService
import com.forestgump.boilerplate.core.domain.entities.DocumentEntity

/**
 * Room database definition
 */
@Database(
    exportSchema = true,
    version = 1,
    entities = [
        DocumentEntity::class
    ]
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun news(): DocumentsLocalService
}
