package kr.loner.feature.bookmark

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kr.loner.core.designsystem.theme.DuskGray
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.core.designsystem.theme.PaleGray
import kr.loner.core.designsystem.theme.Purple01
import kr.loner.core.designsystem.theme.surfaceDim
import kr.loner.core.domain.usecase.GetBookmarkedSessionIdListUseCase
import kr.loner.core.domain.usecase.GetBookmarkedSessionListUseCase


@Composable
internal fun BookmarkRoute(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val bookmarkUiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding()

    ) {

        BookmarkContent(
            uiState = bookmarkUiState,
            onClickEditButton = { viewModel.clickEditButton() })
    }
}

@Composable
private fun BookmarkContent(
    uiState: BookmarkUiState,
    onClickEditButton: () -> Unit
) {
    when (uiState) {
        BookmarkUiState.Loading -> BookmarkLoading()
        is BookmarkUiState.Success -> BookmarkScreen(
            isEditMode = uiState.isEditButtonSelected,
            bookmarkItems = uiState.bookmarks.toImmutableList(),
            onClickEditButton = onClickEditButton
        )
    }
}

@Composable
private fun BookmarkLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun BookmarkScreen(
    isEditMode: Boolean,
    bookmarkItems: ImmutableList<BookmarkItemUiState>,
    onClickEditButton: () -> Unit,
    listContentBottomPadding: Dp = 72.dp
) {
    Column(
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            .background(color = PaleGray),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BookmarkTopAppBar(isEditMode, onClickEditButton)
        if (bookmarkItems.isEmpty()) {
            BookmarkEmptyScreen()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = listContentBottomPadding)
            ) {
                items(
                    items = bookmarkItems,
                    key = { item -> item.session.id }
                ) { itemState ->
                    BookmarkItem(
                        leadingContent = @Composable {
                            BookmarkTimelineItem(
                                sequence = itemState.sequence,
                                time = itemState.time
                            )
                        },
                        midContent = @Composable {
                            BookmarkCard(
                                tagLabel = itemState.tagLabel,
                                room = itemState.session.room,
                                title = itemState.session.title,
                                speaker = itemState.speakerLabel
                            )
                        },
                        trailingContent = @Composable { },
                        isShowTrailingContent = itemState.isEditMode
                    )
                }
            }
        }
    }
}

@Composable
private fun BookmarkEmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.empty_bookmark_item_description),
            style = LonerTheme.typography.titleSmallM,
            color = DuskGray
        )
    }
}

@Composable
private fun BookmarkTopAppBar(
    isEditMode: Boolean,
    onClickEditButton: () -> Unit
) {
    val editButtonColor by animateColorAsState(
        label = "Edit Button Color Animation",
        targetValue = if (isEditMode) {
            Purple01
        } else {
            DuskGray
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.book_mark_top_bar_title),
            style = LonerTheme.typography.titleSmallM,
            color = DuskGray
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = onClickEditButton)
                .padding(8.dp),
            text = if (isEditMode) {
                stringResource(id = R.string.edit_button_confirm_label)
            } else {
                stringResource(id = R.string.edit_button_edit_label)
            },
            style = LonerTheme.typography.titleSmallM,
            color = editButtonColor
        )
    }
}
