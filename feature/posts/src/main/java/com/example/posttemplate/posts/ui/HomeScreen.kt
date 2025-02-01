package com.example.posttemplate.posts.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.posttemplate.posts.domain.model.Post
import com.example.posttemplate.posts.ui.components.PostItem
import com.example.posttemplate.ui.components.LoadingIndicator

@Composable
fun HomeScreen(
    state: HomeState,
    onRetry: () -> Unit,
    onNavigateToDetails: (Int) -> Unit
) {
    when (state) {
        is HomeState.Loading -> LoadingIndicator()
        is HomeState.Error -> Text("Error: ${state.errorMessage}")
        is HomeState.Empty -> Text("No posts available")
        is HomeState.Success -> LazyColumn {
            items(state.posts) { post ->
                PostItem(post = post) { onNavigateToDetails(post.id) }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState.Success(
            posts = listOf(
                Post(
                    id = 1,
                    title = "First Post",
                    body = "This is the body of the first post.",
                    authorId = 1
                ),
                Post(
                    id = 2,
                    title = "Second Post",
                    body = "This is the body of the second post.",
                    authorId = 2
                )
            ),
        ),
        onRetry = {},
        onNavigateToDetails = {}
    )
}
