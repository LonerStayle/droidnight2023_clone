package kr.loner.feature.contributor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.loner.core.domain.usecase.GetContributorListUseCase
import kr.loner.core.model.Contributor
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    getContributorListUseCase: GetContributorListUseCase
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val uiState: StateFlow<ContributorListUiState> = flow { emit(getContributorListUseCase()) }
        .map { ContributorListUiState.ContributorList(it.toPersistentList()) }
        .catch { c -> _errorFlow.emit(c) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            ContributorListUiState.Loading
        )


}