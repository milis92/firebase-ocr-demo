package com.forestgump.boilerplate.ui.splash

import androidx.test.filters.SmallTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test

@SmallTest
class SplashPresenterTest {

    private val presenter = SplashPresenterImp()
    private val view = mock<SplashView>()

    @Before
    fun setUp() {
        presenter.onCreate(view)
    }

    @Test
    fun verifyCredentialsLaunchesHome() {
        presenter.onCreated()

        verify(view, times(1)).onLaunchHome()
        verifyNoMoreInteractions(view)
    }
}
