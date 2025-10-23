package ar.arstan.vkcustom.domain

import ar.arstan.vkcustom.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val avatarResId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long comment text",
    val publicationDate: String = "14:00"
)
