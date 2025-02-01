package com.example.posttemplate.posts.ui

import com.example.posttemplate.posts.domain.model.Post

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val posts: List<Post>) : HomeState()
    data class Error(val errorMessage: String) : HomeState()
    object Empty : HomeState()
}

sealed class HomeIntent {
    object LoadPosts : HomeIntent()
    data class SelectPost(val postId: Int) : HomeIntent()
}

sealed class HomeEffect {
    data class ShowError(val message: String) : HomeEffect()
    data class NavigateToPostDetails(val postId: Int) : HomeEffect()
}
