package com.forestgump.boilerplate.core.domain.interactors

import com.forestgump.boilerplate.core.dagger.qualifiers.IoScheduler
import com.forestgump.boilerplate.core.dagger.qualifiers.MainScheduler
import com.forestgump.boilerplate.core.domain.FlowableUseCase
import com.forestgump.boilerplate.core.domain.SingleUseCase
import com.forestgump.boilerplate.core.domain.UseCase
import com.forestgump.boilerplate.core.domain.models.Document
import com.forestgump.boilerplate.core.domain.repositories.DocumentRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetProcessedDocumentUseCase @Inject constructor(
        @IoScheduler override val ioScheduler: Scheduler,
        @MainScheduler override val mainScheduler: Scheduler,
        private val repository: DocumentRepository
) : SingleUseCase<Document, GetProcessedDocumentUseCase.Params>() {

    override fun run(params: Params): Single<Document> = repository.getSingle(params.id)

    data class Params(val id: String)
}