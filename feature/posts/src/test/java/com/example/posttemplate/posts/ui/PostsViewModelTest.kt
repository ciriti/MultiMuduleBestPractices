package com.example.posttemplate.posts.ui

import app.cash.turbine.turbineScope
import com.example.posttemplate.posts.domain.model.Post
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostsViewModelTest {

    private val mockPostService: com.example.posttemplate.posts.domain.service.PostsService =
        mockk()
    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(service = mockPostService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load posts intent updates state and emits no effect on success`() = testScope.runTest {
        turbineScope {
            // Arrange
            val mockPosts = listOf(
                Post(id = 1, title = "Post 1", body = "Body 1", authorId = 1),
                Post(id = 2, title = "Post 2", body = "Body 2", authorId = 2)
            )
            coEvery { mockPostService.getPosts() } returns Result.success(mockPosts)

            val states = viewModel.state.testIn(backgroundScope)
            val effects = viewModel.effect.testIn(backgroundScope)

            // Act
            viewModel.handleIntent(HomeIntent.LoadPosts)

            // Assert
            assertEquals(HomeState.Success(emptyList()), states.awaitItem()) // State during loading
            assertEquals(HomeState.Loading, states.awaitItem()) // State during loading
            assertEquals(HomeState.Success(mockPosts), states.awaitItem())

            coVerify { mockPostService.getPosts() }

            states.cancel()
            effects.cancel()
        }
    }

    @Test
    fun `load posts intent updates state and emits effect on error`() = testScope.runTest {
        turbineScope {
            // Arrange
            val errorMessage = "Failed to load posts"
            coEvery { mockPostService.getPosts() } returns Result.failure(Exception(errorMessage))

            val states = viewModel.state.testIn(backgroundScope)
            val effects = viewModel.effect.testIn(backgroundScope)

            // Act
            viewModel.handleIntent(HomeIntent.LoadPosts)

            // Assert
            assertEquals(
                HomeState.Success(emptyList()),
                states.awaitItem()
            )
            assertEquals(
                HomeState.Loading,
                states.awaitItem()
            )
            assertEquals(
                HomeState.Error(errorMessage),
                states.awaitItem()
            )
            assertEquals(HomeEffect.ShowError(errorMessage), effects.awaitItem())

            coVerify { mockPostService.getPosts() }

            states.cancel()
            effects.cancel()
        }
    }

    @Test
    fun `select post emits navigate to post details effect`() = testScope.runTest {
        turbineScope {
            // Arrange
            val postId = 1
            val effects = viewModel.effect.testIn(backgroundScope)

            // Act
            viewModel.handleIntent(HomeIntent.SelectPost(postId))

            // Assert
            assertEquals(HomeEffect.NavigateToPostDetails(postId), effects.awaitItem())

            effects.cancel()
        }
    }
}
