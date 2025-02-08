package com.example.posttemplate.ui.screen.auth

// @OptIn(ExperimentalCoroutinesApi::class)
// class AuthenticationViewModelTest {
//
//    private val mockAuthRepository: AuthRepository = mockk {
//        justRun { setUserSignedIn(any()) }
//    }
//    private lateinit var viewModel: AuthenticationViewModel
//    private val testDispatcher = StandardTestDispatcher()
//    private val testScope = TestScope(testDispatcher)
//
//    @Before
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//        viewModel =
//            AuthenticationViewModel(authRepository = mockAuthRepository)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `authenticate intent updates state and emits effect`() = testScope.runTest {
//        turbineScope {
//            // Arrange
//            val states = viewModel.state.testIn(backgroundScope)
//            val effects = viewModel.effect.testIn(backgroundScope)
//
//            // Act
//            viewModel.handleIntent(AuthenticationIntent.Authenticate)
//
//            // Assert
//            assertEquals(false, states.awaitItem().isLoading)
//            assertEquals(true, states.awaitItem().isLoading) // State during loading
//            assertEquals(false, states.awaitItem().isLoading) // State after loading is complete
//            assertEquals(AuthenticationEffect.NavigateToHome, effects.awaitItem())
//
//            coVerify { mockAuthRepository.setUserSignedIn(true) }
//
//            states.cancel()
//        }
//    }
// }
