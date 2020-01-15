package com.forestgump.boilerplate.home.ui.dashboard

import com.nhaarman.mockitokotlin2.mock
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before

class HomePresenterTest {

    private val testScheduler = Schedulers.trampoline()
    private val view = mock<HomeView>()

    private val presenter = HomePresenterImp()

    @Before
    fun setUp() {
        presenter.onCreate(view)
    }

    @After
    fun cleanUp() {
        presenter.onDestroy()
    }
}
