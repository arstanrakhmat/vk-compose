package ar.arstan.vkcustom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.arstan.vkcustom.navigation.AppNavGraph
import ar.arstan.vkcustom.navigation.Screen
import ar.arstan.vkcustom.ui.theme.Black500
import ar.arstan.vkcustom.ui.theme.Black900

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navHostController.navigate(route = item.screen.route) {
                                popUpTo(Screen.NewsFeed.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
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
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = { HomeScreen(viewModel = viewModel, paddingValues = innerPadding) },
            favoriteScreenContent = {
                TextCounter(
                    name = "Favorite",
                    paddingValues = innerPadding
                )
            },
            profileScreenContent = { TextCounter(name = "Profile", paddingValues = innerPadding) }
        )
    }
}

@Composable
private fun TextCounter(
    name: String,
    paddingValues: PaddingValues
) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .clickable { count++ },
        text = "$name Count: $count",
        color = Black900
    )

}