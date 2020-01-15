package com.forestgump.boilerplate.core.domain.interactors

import com.forestgump.boilerplate.core.dagger.qualifiers.IoScheduler
import com.forestgump.boilerplate.core.dagger.qualifiers.MainScheduler
import com.forestgump.boilerplate.core.domain.SingleUseCase
import com.forestgump.boilerplate.core.domain.UseCase
import com.forestgump.boilerplate.core.domain.models.News
import com.forestgump.boilerplate.core.domain.repositories.NewsRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    @IoScheduler override val ioScheduler: Scheduler,
    @MainScheduler override val mainScheduler: Scheduler,
    private val repository: NewsRepository
) : SingleUseCase<News, UseCase.None>() {
    override fun run(params: None): Single<News> = repository.getAll()
}
