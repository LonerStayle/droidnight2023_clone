package kr.loner.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.loner.core.domain.usecase.GetSponsorListUseCase
import kr.loner.core.model.Sponsor
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getSponsorListUseCase:GetSponsorListUseCase
):ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow:SharedFlow<Throwable> get() = _errorFlow

    val sponsorListUiState:StateFlow<SponsorListUiState> = flow {  emit(getSponsorListUseCase()) }
        .map { sponsorList ->
            if(sponsorList.isNotEmpty()){
                SponsorListUiState.SponsorList(sponsorList)
            }else{
                SponsorListUiState.Empty
            }
        }.catch { throwable ->
            _errorFlow.emit(throwable)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SponsorListUiState.Loading
        )
}