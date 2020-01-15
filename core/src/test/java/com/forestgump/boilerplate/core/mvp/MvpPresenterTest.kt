package com.forestgump.boilerplate.core.mvp

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.forestgump.boilerplate.core.common.mvp.MvpPresenter
import com.forestgump.boilerplate.core.common.mvp.MvpView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@SmallTest
@RunWith(MockitoJUnitRunner::class)
class MvpPresenterTest {

    @InjectMocks
    lateinit var presenter: MvpPresenter<MvpView>

    @Mock
    lateinit var viewMock: MvpView

    @Before
    fun setUp() {
        presenter.onCreate(viewMock)
    }

    @Test
    fun `onCreate attaches the view`() {
        presenter.onCreate(viewMock)

        assertThat(presenter.viewReference?.get()).isSameInstanceAs(viewMock)
        assertThat(presenter.view).isSameInstanceAs(viewMock)
    }

    @Test
    fun `onDestroy clears the reference`() {
        presenter.onDestroy()

        assertThat(presenter.viewReference?.get()).isNull()
        assertThat(presenter.view).isNull()
    }
}
