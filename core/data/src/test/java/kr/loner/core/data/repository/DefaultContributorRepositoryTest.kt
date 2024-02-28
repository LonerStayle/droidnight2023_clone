package kr.loner.core.data.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kr.loner.core.data.fake.api.FakeGithubApi
import kr.loner.core.data.model.ContributorResponse
import kr.loner.core.data.repository.impl.DefaultContributorRepository
import kr.loner.core.model.Contributor

internal class DefaultContributorRepositoryTest : BehaviorSpec() {
    private val repository = DefaultContributorRepository(
        FakeGithubApi(contributors)
    )

    init {
        Given("컨트리뷰터가 존재한다") {
            val expected = contributors
            When("컨트리뷰터를 조회한다") {
                val contributorsApi: List<Contributor> = repository.getContributors(
                    owner = "droidknights", name = "app2023"
                )
                Then("컨트리뷰터를 반환한다") {
                    contributorsApi.size shouldBe 1
                    contributorsApi.all {
                        it.name == expected[0].name
                    }
                }
            }
        }
    }

    companion object {
        private val contributors = listOf(
            ContributorResponse(
                name = "test", imageUrl = "testImgUrl", githubUrl = "testGitUrl"
            )
        )
    }
}