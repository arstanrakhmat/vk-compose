package ar.arstan.vkcustom

import ar.arstan.vkcustom.domain.FeedPost

sealed class HomeScreenState {

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val comments: List<Comments>) : HomeScreenState()
}