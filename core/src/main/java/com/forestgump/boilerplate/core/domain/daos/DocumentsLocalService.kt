package com.forestgump.boilerplate.core.domain.daos

import androidx.room.*
import com.forestgump.boilerplate.core.domain.entities.DocumentEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
internal abstract class DocumentsLocalService {

    @Query("SELECT * FROM documents")
    abstract fun getAll(): Flowable<List<DocumentEntity>>

    @Query("SELECT * FROM documents where id=:id")
    abstract fun getSingle(id: String) : Single<DocumentEntity>

    @Query("DELETE FROM documents")
    abstract fun deleteAll()

    @Query("DELETE FROM documents where id=:fileToken")
    abstract fun delete(fileToken: String): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg storageFileEntity: DocumentEntity) : Completable

    @Transaction
    open fun sync(data: List<DocumentEntity>) {
        deleteAll()
        insert(*data.toTypedArray())
    }


}
