package com.forestgump.boilerplate.core.domain.usecase

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.forestgump.boilerplate.core.domain.NetworkFailure
import com.forestgump.boilerplate.core.domain.SingleUseCase
import com.forestgump.boilerplate.core.domain.UseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import org.junit.Test

@SmallTest
class SingleUseCaseTest {

    @Test
    fun singleUseCaseReturnsValue() {
        val expected = "test"
        val useCase = object : SingleUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Single<String> = Single.just(expected)
        }

        useCase(UseCase.None, onSuccess = {
            assertThat(expected).isEqualTo("test")
        })

        useCase.clear()
    }

    @Test
    fun singleUseCaseReturnsError() {
        val expected = Throwable()
        val useCase = object : SingleUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Single<String> = Single.error(expected)
        }

        useCase(UseCase.None, onError = {
            assertThat(it.throwable).isEqualTo(expected)
        })

        useCase.clear()
    }

    @Test
    fun singleUseCaseMapsErrors() {
        val expected = UnknownHostException()
        val useCase = object : SingleUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Single<String> = Single.error(expected)
        }

        useCase(UseCase.None, onError = {
            assertThat(it).isInstanceOf(NetworkFailure::class.java)
        })

        useCase.clear()
    }
}
