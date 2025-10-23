package ar.arstan.vkcustom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.arstan.vkcustom.domain.FeedPost
import ar.arstan.vkcustom.domain.PostComment
import ar.arstan.vkcustom.domain.StatisticItem

class MainViewModel : ViewModel() {

    private val comments = mutableListOf<PostComment>().apply {
        repeat(10) {
            add(PostComment(id = it))
        }
    }

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val initialState = HomeScreenState.Posts(sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var savedState: HomeScreenState = initialState

    fun showComments(post: FeedPost) {
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Comments(post = post, comments = comments)
    }

    fun closeComments() {
        _screenState.value = savedState
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) {
            return
        }

        val oldPosts =
            currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newsPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = HomeScreenState.Posts(posts = newsPosts)
    }

//    fun remove(feedPost: FeedPost) {
//        val currentState = _screenState.value
//        if (currentState !is HomeScreenState.Posts) {
//            return
//        }
//        val oldPosts = currentState.posts.toMutableList()
//        oldPosts.remove(feedPost)
//        _screenState.value = HomeScreenState.Posts(posts = oldPosts)
//    }
}