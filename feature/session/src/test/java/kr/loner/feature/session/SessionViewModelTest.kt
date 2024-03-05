package kr.loner.feature.session

import app.cash.turbine.test
import io.kotest.inspectors.runTest
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kr.loner.core.domain.usecase.GetBookmarkedSessionIdListUseCase
import kr.loner.core.domain.usecase.GetSessionListUseCase
import kr.loner.core.model.Level
import kr.loner.core.model.Room
import kr.loner.core.model.Session
import kr.loner.core.testing.rule.MainDispatcherRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class SessionViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getSessionListUseCase:GetSessionListUseCase = mockk()
    private val getBookmarkedSessionIdListUseCase: GetBookmarkedSessionIdListUseCase = mockk()
    private lateinit var viewModel: SessionViewModel

    private val fakeSession = Session(
        id = "1",
        title = "title",
        content = "content",
        speakers = emptyList(),
        level = Level.BASIC,
        tags = emptyList(),
        room = Room.TRACK1,
        startTime = LocalDateTime(2023, 9, 12, 13, 0, 0),
        endTime = LocalDateTime(2023, 9, 12, 13, 30, 0),
        isBookmarked = false
    )

    @Test
    fun `세션 데이터를 확인할 수 있다`() = runTest{
        //given
        coEvery { getSessionListUseCase() } returns listOf(fakeSession)
        coEvery { getBookmarkedSessionIdListUseCase() } returns flowOf(emptySet())
        viewModel = SessionViewModel(getSessionListUseCase, getBookmarkedSessionIdListUseCase)

        viewModel.uiState.test {
            val actual = (awaitItem() as? SessionUiState.SessionList)?.sessionList?.first()
            assertEquals(fakeSession,actual)
        }
    }
}