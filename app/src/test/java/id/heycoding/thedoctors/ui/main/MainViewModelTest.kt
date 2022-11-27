package id.heycoding.thedoctors.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import id.heycoding.thedoctors.utils.getOrAwaitValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainViewModel: MainViewModel

    @Test
    fun `when get isLoading from view model should return LiveData Boolean`() {
        val expectedData = MutableLiveData<Boolean>()
        val dummyIsLoading = true
        expectedData.value = dummyIsLoading

        Mockito.`when`(mainViewModel.isLoading).thenReturn(expectedData)

        val actualData = mainViewModel.isLoading.getOrAwaitValue()

        Mockito.verify(mainViewModel).isLoading
        assertThat(actualData).isEqualTo(expectedData.value)
    }

    @Test
    fun `when get message from view model should return LiveData Boolean`() {
        val expectedData = MutableLiveData<String>()
        val dummyMessage = "dummyMessage"
        expectedData.value = dummyMessage

        Mockito.`when`(mainViewModel.message).thenReturn(expectedData)

        val actualData = mainViewModel.message.getOrAwaitValue()

        Mockito.verify(mainViewModel).message
        assertThat(actualData).isEqualTo(expectedData.value)
    }
}