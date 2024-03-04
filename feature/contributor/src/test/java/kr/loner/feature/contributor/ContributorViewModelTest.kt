package kr.loner.feature.contributor

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kr.loner.core.domain.usecase.GetContributorListUseCase
import kr.loner.core.model.Contributor
import kr.loner.core.testing.rule.MainDispatcherRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
internal class ContributorViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getContributorsUseCase:GetContributorListUseCase = mockk()
    private lateinit var viewModel: ContributorViewModel

    @Test
    fun `컨트리뷰터 데이터를 확인할 수 있다`() = runTest{
        //given
        coEvery { getContributorsUseCase() } returns fakeContributors
        viewModel = ContributorViewModel(getContributorsUseCase)

        //when & then
        viewModel.uiState.test {
            val actual:ContributorListUiState = awaitItem()
            assertIs<ContributorListUiState.ContributorList>(actual)
        }
    }

    companion object{
        private val fakeContributors = listOf(
            Contributor(
                name = "test name",
                imageUrl = "test image url",
                githubUrl = "test github url"
            )
        )
    }
}