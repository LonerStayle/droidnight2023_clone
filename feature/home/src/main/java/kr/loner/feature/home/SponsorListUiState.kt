package kr.loner.feature.home

import kr.loner.core.model.Sponsor

sealed interface SponsorListUiState {
    data object Loading: SponsorListUiState
    data object Empty : SponsorListUiState

    data class SponsorList(
        val sponsorList:List<Sponsor>
    ): SponsorListUiState{
        val platinumCount:Int
            get() = sponsorList.count{it.grade == Sponsor.Grade.PLATINUM}

        val goldCount:Int
            get() = sponsorList.count{it.grade == Sponsor.Grade.GOLD}
    }
}