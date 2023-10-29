package com.example.androidkotlintemplate

import app.cash.turbine.test
import com.example.androidkotlintemplate.database.CharactersDatabaseMapper
import com.example.androidkotlintemplate.network.ApiMapper
import com.example.androidkotlintemplate.network.ApiService
import com.example.androidkotlintemplate.network.CharactersRepository
import com.example.androidkotlintemplate.screen.ApiStatus
import com.example.androidkotlintemplate.screen.CharactersUiMapper
import com.example.androidkotlintemplate.screen.MainViewModelImpl
import com.example.androidkotlintemplate.screen.ScreenData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

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
    private val subject = MainViewModelImpl(
        apiService,
        charactersRepository,
        charactersUiMapper,
        charactersDatabaseMapper,
        apiMapper
    )

    @Test
    fun `Given successful flow Then final status is ApiStatus_Done`() {
        coEvery { charactersRepository.characters() } returns listOf()
        coEvery { charactersUiMapper.mapCharacters(any()) } returns listOf()
        coEvery { apiService.getCharacters(any(), any(), any()) } returns mockk()
        coEvery { apiService.getCharacter(any(), any(), any(), any()) } returns mockk()
        runTest {
            subject.status.test {
                subject.init()

                assertEquals(ApiStatus.Loading, awaitItem())
                assertEquals(ApiStatus.Done, awaitItem())
                ensureAllEventsConsumed()
            }
        }
    }

    @Test
    fun `Given unsuccessful call  Then final status is ApiStatus_Done but reports Error`() {
        coEvery { charactersRepository.characters() } returns listOf()
        coEvery { charactersUiMapper.mapCharacters(any()) } returns listOf()
        coEvery { apiService.getCharacters(any(), any(), any()) } returns mockk()
        coEvery { apiService.getCharacter(any(), any(), any(), any()) } throws Exception()
        runTest {
            subject.status.test {
                subject.init()

                assertEquals(ApiStatus.Loading, awaitItem())
                assertEquals(ApiStatus.Error, awaitItem())
                assertEquals(ApiStatus.Done, awaitItem())
                ensureAllEventsConsumed()
            }
        }
    }

    @Test
    fun `Given successful flow Then screenData got initialised `() {
        coEvery { charactersRepository.characters() } returns listOf()
        coEvery { charactersUiMapper.mapCharacters(any()) } returns listOf()
        coEvery { apiService.getCharacters(any(), any(), any()) } returns mockk(relaxed = true)
        coEvery {
            apiService.getCharacter(
                any(),
                any(),
                any(),
                any()
            )
        } returns mockk(relaxed = true)

        runTest {
            subject.screenData.test {
                subject.init()

                assertEquals(ScreenData(), awaitItem())
                ensureAllEventsConsumed()
            }
        }
    }
}
