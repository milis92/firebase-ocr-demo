package com.forestgump.boilerplate.core.domain.interactors

import android.net.Uri
import com.forestgump.boilerplate.core.dagger.qualifiers.IoScheduler
import com.forestgump.boilerplate.core.dagger.qualifiers.MainScheduler
import com.forestgump.boilerplate.core.domain.CompletableUseCase
import com.forestgump.boilerplate.core.domain.repositories.DocumentRepository
import io.reactivex.Scheduler
import javax.inject.Inject

class ProcessImageUseCase @Inject constructor(
        @IoScheduler override val ioScheduler: Scheduler,
        @MainScheduler override val mainScheduler: Scheduler,
        private val repository: DocumentRepository
) : CompletableUseCase<ProcessImageUseCase.Params>() {

    override fun run(params: Params) = repository.detectText(params.fileUri)

    data class Params(val fileUri: Uri)
}
