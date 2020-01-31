package com.forestgump.boilerplate.core.domain.repositories

import android.content.Context
import android.net.Uri
import com.forestgump.boilerplate.core.domain.Repository
import com.forestgump.boilerplate.core.domain.daos.DocumentsLocalService
import com.forestgump.boilerplate.core.domain.entities.DocumentEntity
import com.forestgump.boilerplate.core.domain.models.Document
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DocumentRepository : Repository {
    fun sync(): Completable
    fun getAll(): Flowable<List<Document>>
    fun detectText(uri: Uri): Completable
    fun getSingle(id: String): Single<Document>
}

internal class DocumentRepositoryImpl constructor(
        private val applicationContext: Context,
        private val localService: DocumentsLocalService,
        private val detector: FirebaseVisionTextRecognizer
) : DocumentRepository {

    override fun sync(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): Flowable<List<Document>> = localService.getAll().map {
        it.map { it.toModel() }
    }

    override fun detectText(uri: Uri): Completable = Single.create<String> { singleFuture ->
        detector.processImage(FirebaseVisionImage.fromFilePath(applicationContext, uri))
                .addOnSuccessListener {
                    singleFuture.onSuccess(it.text)
                }.addOnFailureListener {
                    singleFuture.onError(it)
                }
    }.observeOn(Schedulers.io())
            .flatMapCompletable {
                localService.insert(DocumentEntity(extractedText = it, uri = uri.toString()))
            }

    override fun getSingle(id: String): Single<Document> = localService.getSingle(id).map { it.toModel() }


}
