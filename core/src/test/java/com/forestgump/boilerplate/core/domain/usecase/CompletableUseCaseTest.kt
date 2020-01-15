package com.forestgump.boilerplate.core.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.forestgump.boilerplate.core.domain.CompletableUseCase
import com.forestgump.boilerplate.core.domain.NetworkFailure
import com.forestgump.boilerplate.core.domain.UseCase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import org.junit.Test

class CompletableUseCaseTest {

    @Test
    fun completableUseCaseReturnsData() {
        val useCase = object : CompletableUseCase<UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Completable = Completable.complete()
        }
        useCase(UseCase.None, onComplete = {
            // TODO See how we should test lambdas
        })

        useCase.clear()
    }

    @Test
    fun completableUseCaseReturnsError() {
        val expected = Throwable()
        val useCase = object : CompletableUseCase<UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Completable = Completable.error(expected)
        }

        useCase(UseCase.None, onError = {
            assertThat(it.throwable).isEqualTo(expected)
        })

        useCase.clear()
    }

    @Test
    fun completableUseCaseMapsErrors() {
        val expected = UnknownHostException()
        val useCase = object : CompletableUseCase<UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Completable = Completable.error(expected)
        }

        useCase(UseCase.None, onError = {
            assertThat(it).isInstanceOf(NetworkFailure::class.java)
        })

        useCase.clear()
    }
}
