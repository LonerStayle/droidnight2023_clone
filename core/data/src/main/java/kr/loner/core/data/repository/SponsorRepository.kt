package kr.loner.core.data.repository

import kr.loner.core.model.Sponsor

interface SponsorRepository {
    suspend fun getSponsorList(): List<Sponsor>
}