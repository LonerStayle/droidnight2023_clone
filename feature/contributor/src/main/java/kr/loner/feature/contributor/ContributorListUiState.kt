package kr.loner.feature.contributor

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList
import kr.loner.core.model.Contributor

sealed interface ContributorListUiState {
    data object Loading : ContributorListUiState

    @Stable
    data class ContributorList(
        val contributorList:PersistentList<Contributor>
    ):ContributorListUiState
}