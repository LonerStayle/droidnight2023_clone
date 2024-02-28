package kr.loner.core.data.fake.datasoruce

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kr.loner.core.datastore.datasource.SessionPreferencesDataSource

class FakeSessionPreferencesDataSource : SessionPreferencesDataSource {
    private val _bookmarkedSession = MutableStateFlow(emptySet<String>())
    override val bookmarkedSession: Flow<Set<String>> = _bookmarkedSession.filterNotNull()
    override suspend fun updateBookmarkedSession(bookmarkedSession: Set<String>) {
        _bookmarkedSession.value = bookmarkedSession.toSet()
    }
}