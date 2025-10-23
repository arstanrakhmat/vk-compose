package ar.arstan.vkcustom

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ar.arstan.vkcustom.ui.theme.Black500
import ar.arstan.vkcustom.ui.theme.Black900

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val selectedNavItem by viewModel.selectedNavItem.observeAsState(NavigationItem.Home)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedNavItem == item,
                        onClick = { viewModel.selectNavItem(item) },
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
        when (selectedNavItem) {

            NavigationItem.Home -> {
                HomeScreen(viewModel = viewModel, paddingValues = innerPadding)
            }

            NavigationItem.Favorite -> {
                TextCounter(name = "Favorite")
            }

            NavigationItem.Profile -> {
                TextCounter(name = "Profile")
            }
        }
    }
}

@Composable
private fun TextCounter(
    name: String
) {
    var count by remember {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Black900
    )

}