package kr.loner.core.data.repository

import kr.loner.core.model.Contributor

interface ContributorRepository {
    suspend fun getContributors(
        owner:String,
        name:String
    ):List<Contributor>
}