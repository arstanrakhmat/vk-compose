package ar.arstan.vkcustom.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ar.arstan.vkcustom.MainViewModel
import ar.arstan.vkcustom.PostCard
import ar.arstan.vkcustom.domain.FeedPost

@Composable
fun MainScreen(viewModel: MainViewModel) {

    Scaffold(
        bottomBar = {
            val selectedItemPosition = remember {
                mutableIntStateOf(0)
            }

            NavigationBar {
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition.intValue == index,
                        onClick = { selectedItemPosition.intValue = index },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        colors = NavigationBarItemColors(
                            selectedIconColor = Black900,
                            selectedTextColor = Black900,
                            selectedIndicatorColor = Color.Transparent,
                            unselectedIconColor = Black500,
                            unselectedTextColor = Black500,
                            disabledIconColor = Black500,
                            disabledTextColor = Black500
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        val feedPost = viewModel.feedPost.observeAsState(FeedPost())

        PostCard(
            modifier = Modifier.padding(innerPadding),
            feedPost = feedPost.value,
            onLikeClickListener = { viewModel.updateCount(item = it) },
            onShareClickListener = { viewModel.updateCount(item = it) },
            onViewsClickListener = { viewModel.updateCount(item = it) },
            onCommentClickListener = { viewModel.updateCount(item = it) }
        )
    }
}