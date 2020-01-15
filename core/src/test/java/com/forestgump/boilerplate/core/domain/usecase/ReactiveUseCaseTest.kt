package com.forestgump.boilerplate.core.domain.usecase

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.spy
import com.forestgump.boilerplate.core.domain.ReactiveUseCase
import io.reactivex.observers.DisposableCompletableObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@SmallTest
@RunWith(MockitoJUnitRunner::class)
class ReactiveUseCaseTest {

    @Test
    fun `clear clears and disposes jobs`() {
        val useCase = spy<ReactiveUseCase<String, Nothing>>()

        useCase.disposables.add(object : DisposableCompletableObserver() {
            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
            }
        })
        useCase.clear()

        assertThat(useCase.disposables.isDisposed).isTrue()
        assertThat(useCase.disposables.size()).isEqualTo(0)
    }
}
