package kr.loner.feature.contributor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.collections.immutable.persistentListOf
import kr.loner.core.model.Contributor
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ContributorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val fakeUiState: MutableState<ContributorListUiState> =
        mutableStateOf(ContributorListUiState.Loading)


    @Before
    fun setup(){
        composeTestRule.setContent {
            ContributorScreen(uiState = fakeUiState.value, onBackClick = { })
        }
    }

    @Test
    fun 로딩_상태일때는_리스트가_노출되지_않는다(){
        //when
        fakeUiState.value = ContributorListUiState.Loading

        //then
        composeTestRule
            .onNodeWithText("ContributorList")
            .assertDoesNotExist()
    }

    @Test
    fun 컨트리뷰터_리스트가_주어지면_화면에_노출한다(){
        //when
        fakeUiState.value = ContributorListUiState.ContributorList(
            persistentListOf(
                Contributor(
                    name = "test name",
                    imageUrl = "test image url",
                    githubUrl = "test github url"
                )
            )
        )

        //then
        composeTestRule
            .onNodeWithText("test name")
            .assertExists()
    }
}