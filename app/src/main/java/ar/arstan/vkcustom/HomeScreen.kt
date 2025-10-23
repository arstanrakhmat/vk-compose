package ar.arstan.vkcustom

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.arstan.vkcustom.domain.FeedPost

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)

    when (val currentState = screenState.value) {
        is HomeScreenState.Posts -> {
            FeedPosts(
                posts = currentState.posts,
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }

        is HomeScreenState.Comments -> {
            CommentsScreen(
                feedPost = currentState.post,
                comments = currentState.comments,
                onBackPressed = {
                    viewModel.closeComments()
                }
            )
            BackHandler {
                viewModel.closeComments()
            }
        }

        is HomeScreenState.Initial -> {

        }
    }
}

@Composable
private fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { feedPost ->
            PostCard(
                feedPost = feedPost,
                onLikeClickListener = { statisticItem ->
                    viewModel.updateCount(feedPost, statisticItem)
                },
                onShareClickListener = { statisticItem ->
                    viewModel.updateCount(feedPost, statisticItem)
                },
                onViewsClickListener = { statisticItem ->
                    viewModel.updateCount(feedPost, statisticItem)
                },
                onCommentClickListener = {
                    viewModel.showComments(feedPost)
                }
            )
        }
    }
}