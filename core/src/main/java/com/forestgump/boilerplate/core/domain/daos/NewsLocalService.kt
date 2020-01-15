package com.forestgump.boilerplate.core.domain.daos

import androidx.room.*
import com.forestgump.boilerplate.core.domain.entities.NewsEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
internal abstract class NewsLocalService {

    @Query("SELECT * FROM news")
    abstract fun getAll(): Flowable<List<NewsEntity>>

    @Query("DELETE FROM news")
    abstract fun deleteAll()

    @Query("DELETE FROM news where token=:fileToken")
    abstract fun delete(fileToken: String): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg storageFileEntity: NewsEntity)

    @Transaction
    open fun sync(data: List<NewsEntity>) {
        deleteAll()
        insert(*data.toTypedArray())
    }
}
