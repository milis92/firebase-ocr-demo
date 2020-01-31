package com.forestgump.boilerplate.core.domain.interactors

import com.forestgump.boilerplate.core.dagger.qualifiers.IoScheduler
import com.forestgump.boilerplate.core.dagger.qualifiers.MainScheduler
import com.forestgump.boilerplate.core.domain.FlowableUseCase
import com.forestgump.boilerplate.core.domain.UseCase
import com.forestgump.boilerplate.core.domain.models.Document
import com.forestgump.boilerplate.core.domain.repositories.DocumentRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetProcessedDocumentsUseCase @Inject constructor(
        @IoScheduler override val ioScheduler: Scheduler,
        @MainScheduler override val mainScheduler: Scheduler,
        private val repository: DocumentRepository
) : FlowableUseCase<List<Document>, UseCase.None>() {

    override fun run(params: None): Flowable<List<Document>> = repository.getAll()
}