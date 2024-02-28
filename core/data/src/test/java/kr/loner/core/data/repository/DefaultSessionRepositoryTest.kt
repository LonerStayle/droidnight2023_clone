package kr.loner.core.data.repository

import app.cash.turbine.test
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDateTime
import kr.loner.core.data.fake.api.FakeGithubRawApi
import kr.loner.core.data.fake.datasoruce.FakeSessionPreferencesDataSource
import kr.loner.core.data.repository.impl.DefaultSessionRepository
import kr.loner.core.model.Level
import kr.loner.core.model.Room
import kr.loner.core.model.Session

internal class DefaultSessionRepositoryTest : StringSpec() {

    init {
        val repository = DefaultSessionRepository(
            githubRawApi = FakeGithubRawApi(),
            sessionDataSource = FakeSessionPreferencesDataSource()
        )

        "역직렬화 테스트" {
            val expected = Session(
                id = "1",
                title = "Keynote",
                content = "",
                speakers = emptyList(),
                level = Level.ETC,
                tags = emptyList(),
                room = Room.ETC,
                startTime = LocalDateTime(2023, 9, 12, 10, 45),
                endTime = LocalDateTime(2023, 9, 12, 11, 0),
                isBookmarked = false
            )
            val actual = repository.getSessionList()
            actual.first() shouldBe expected
        }

        "북마크 추가 테스트" {
            repository.getBookmarkedSessionIds().test {
                awaitItem() shouldBe emptySet()

                repository.bookmarkSession(sessionId = "1", bookmark = true)
                awaitItem() shouldBe setOf("1")

                repository.bookmarkSession(sessionId = "2", bookmark = true)
                awaitItem() shouldBe setOf("1", "2")
            }
        }

        "북마크 제거 테스트" {
            val bookmarkedSessionIds = listOf("1", "2", "3")
            bookmarkedSessionIds.forEach {
                repository.bookmarkSession(it, true)
            }

            repository.getBookmarkedSessionIds().test {
               awaitItem() shouldBe setOf("1", "2","3")

                repository.bookmarkSession(sessionId = "2", bookmark = false)
                awaitItem() shouldBe setOf("1","3")

                repository.bookmarkSession(sessionId = "3", bookmark = false)
                awaitItem() shouldBe setOf("1")
            }
        }
    }
}