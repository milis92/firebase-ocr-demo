package com.forestgump.boilerplate.core.domain.usecase

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.forestgump.boilerplate.core.domain.MaybeUseCase
import com.forestgump.boilerplate.core.domain.NetworkFailure
import com.forestgump.boilerplate.core.domain.UseCase
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import org.junit.Test

@SmallTest
class MaybeUseCaseTest {

    @Test
    fun `maybe use-case returns value`() {
        val expected = "test"
        val useCase = object : MaybeUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Maybe<String> = Maybe.just(expected)
        }

        useCase(
            params = UseCase.None,
            onSuccess = {
                assertThat(it).isEqualTo(expected)
            })

        useCase.clear()
    }

    @Test
    fun `maybe use-case returns error`() {
        val expected = Throwable()
        val useCase = object : MaybeUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Maybe<String> = Maybe.error(expected)
        }

        useCase(
            params = UseCase.None,
            onError = {
                assertThat(it.throwable).isEqualTo(expected)
            })

        useCase.clear()
    }

    @Test
    fun `maybe use-case maps errors`() {
        val expected = UnknownHostException()
        val useCase = object : MaybeUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Maybe<String> = Maybe.error(expected)
        }

        useCase(UseCase.None, onError = {
            assertThat(it).isInstanceOf(NetworkFailure::class.java)
        })

        useCase.clear()
    }
}
