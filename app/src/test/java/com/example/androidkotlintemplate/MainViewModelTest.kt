package com.example.androidkotlintemplate

import app.cash.turbine.test
import app.cash.turbine.testIn
import app.cash.turbine.turbineScope
import com.example.androidkotlintemplate.database.CharactersDatabaseMapper
import com.example.androidkotlintemplate.network.ApiMapper
import com.example.androidkotlintemplate.network.ApiService
import com.example.androidkotlintemplate.network.CharactersRepository
import com.example.androidkotlintemplate.screen.CharactersUiMapper
import com.example.androidkotlintemplate.screen.MainViewModelImpl
import com.example.androidkotlintemplate.screen.ScreenData
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val apiService: ApiService = mockk()
    private val charactersRepository: CharactersRepository = mockk()
    private val charactersUiMapper: CharactersUiMapper = mockk()
    private val charactersDatabaseMapper: CharactersDatabaseMapper = mockk()
    private val apiMapper: ApiMapper = mockk()
    private lateinit var subject: MainViewModelImpl

    @Before
    fun setUp() {
        subject = MainViewModelImpl(
            apiService,
            charactersRepository,
            charactersUiMapper,
            charactersDatabaseMapper,
            apiMapper
        )
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `uno l`() {

        runTest {
            subject.init()
        }
    }

    @Test
    fun `dos _`() {
        runTest {
            flowOf("one", "two", "three")
                .map {
                    delay(100)
                    it
                }
                .test {
                    // 0 - 100ms -> no emission yet
                    // 100ms - 200ms -> "one" is emitted
                    // 200ms - 300ms -> "two" is emitted
                    // 300ms - 400ms -> "three" is emitted
                    delay(250)
                    assertEquals("two", expectMostRecentItem())
                    cancelAndIgnoreRemainingEvents()
                }
        }
    }

    @Test
    fun `tres`() {
        runTest {
            flowOf("one", "two").test {
                assertEquals("one", awaitItem())
                assertEquals("two", awaitItem())
                awaitComplete()
            }
        }
    }

    @Test
    fun `cuatro se`() {

        runTest {
            turbineScope {
                val turbine1 =
                    flowOf(ScreenData("Test", listOf())).testIn(backgroundScope, name = "test1")
                subject.init()
                turbine1.awaitItem()

            }
        }
    }
}
