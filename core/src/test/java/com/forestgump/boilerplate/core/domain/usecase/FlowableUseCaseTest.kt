package com.forestgump.boilerplate.core.domain.usecase

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.forestgump.boilerplate.core.domain.FlowableUseCase
import com.forestgump.boilerplate.core.domain.NetworkFailure
import com.forestgump.boilerplate.core.domain.UseCase
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import org.junit.Test

@SmallTest
class FlowableUseCaseTest {

    @Test
    fun `flowable use-case returns value`() {
        val expected = "test"
        val useCase = object : FlowableUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Flowable<String> = Flowable.just(expected)
        }

        useCase(UseCase.None, onNext = {
            assertThat(expected).isEqualTo("test")
        })

        useCase.clear()
    }

    @Test
    fun `flowable use-case returns error`() {
        val expected = Throwable()
        val useCase = object : FlowableUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Flowable<String> = Flowable.error(expected)
        }

        useCase(UseCase.None, onError = {
            assertThat(it.throwable).isEqualTo(expected)
        })

        useCase.clear()
    }

    @Test
    fun `flowable use-case streams data`() {
        val expected = listOf("val1", "val2")
        val useCase = object : FlowableUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Flowable<String> = Flowable.fromIterable(expected)
        }

        val actual = mutableListOf<String>()
        useCase(
            params = UseCase.None,
            onNext = {
                actual.add(it)
            })

        assertThat(actual).containsExactly(*expected.toTypedArray())

        useCase.clear()
    }

    @Test
    fun `maybe use-case maps errors`() {
        val expected = UnknownHostException()
        val useCase = object : FlowableUseCase<String, UseCase.None>() {
            override val ioScheduler = Schedulers.trampoline()
            override val mainScheduler = Schedulers.trampoline()
            override fun run(params: None): Flowable<String> = Flowable.error(expected)
        }

        useCase(UseCase.None, onError = {
            assertThat(it).isInstanceOf(NetworkFailure::class.java)
        })

        useCase.clear()
    }
}
