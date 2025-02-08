package com.example.posttemplate.account.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.posttemplate.account.ui.AccountIntent
import com.example.posttemplate.account.ui.AccountScreen
import com.example.posttemplate.account.ui.AccountViewModel
import com.example.posttemplate.ui.navigation.Route

fun NavGraphBuilder.accountRoute(accountViewModel: AccountViewModel) {
    composable(
        route = Route.Account.route + "/{accountId}",
        arguments = listOf(navArgument("accountId") { type = NavType.IntType })
    ) { backStackEntry ->
        val accountId = backStackEntry.arguments?.getInt("accountId") ?: return@composable
        LaunchedEffect(Unit) {
            accountViewModel.handleIntent(AccountIntent.LoadAccount(accountId))
        }
        AccountScreen(
            state = accountViewModel.state.collectAsState().value,
            onBack = { /* Handle navigation back */ },
            onUpdate = { account ->
                accountViewModel.handleIntent(
                    AccountIntent.UpdateAccount(
                        account
                    )
                )
            }
        )
    }
}
