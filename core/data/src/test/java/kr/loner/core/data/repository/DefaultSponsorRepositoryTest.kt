package kr.loner.core.data.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json
import kr.loner.core.data.fake.api.FakeGithubRawApi
import kr.loner.core.data.repository.impl.DefaultSponsorRepository
import kr.loner.core.model.Sponsor

internal class DefaultSponsorRepositoryTest : StringSpec() {
    init {
        val repository: SponsorRepository = DefaultSponsorRepository(
            githubRawApi = FakeGithubRawApi(
                json = Json { ignoreUnknownKeys = true },
            )
        )
        "역직렬화 테스트" {
            val expected = Sponsor(
                name = "당근마켓",
                imageUrl = "https://raw.githubusercontent.com/droidknights/DroidKnights2023_App/main/feature/home/src/main/res/drawable/img_sponsor_daangn.png",
                homepage = "https://about.daangn.com/",
                grade = Sponsor.Grade.PLATINUM,
            )
            val actual = repository.getSponsorList().first()
            actual shouldBe expected
        }
    }
}