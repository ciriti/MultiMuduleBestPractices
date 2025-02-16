package com.example.posttemplate.posts.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.posttemplate.posts.domain.model.Post
import com.example.posttemplate.posts.ui.components.PostItem
import com.example.posttemplate.ui.components.LoadingIndicator

@Composable
fun HomeScreen(
    state: HomeState,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
    onNavigateToDetails: (Int) -> Unit
) {

    when (state) {
        is HomeState.Loading -> LoadingIndicator()
        is HomeState.Error -> Text("Error: ${state.message}")
        is HomeState.Success -> LazyColumn {
            items(state.posts) { post ->
                PostItem(post = post) { onNavigateToDetails(post.id) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState.Success(
            listOf(
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
            )
        ),
        onRetry = {},
        onNavigateToDetails = {}
    )
}
