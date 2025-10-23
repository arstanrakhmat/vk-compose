package ar.arstan.vkcustom

import ar.arstan.vkcustom.domain.FeedPost
import ar.arstan.vkcustom.domain.PostComment

sealed class HomeScreenState {

    object Initial: HomeScreenState()
    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val post: FeedPost, val comments: List<PostComment>) : HomeScreenState()
}