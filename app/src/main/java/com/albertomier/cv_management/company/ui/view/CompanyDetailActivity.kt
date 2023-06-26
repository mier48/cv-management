package com.albertomier.cv_management.company.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.albertomier.cv_management.company.ui.screens.AddInterviewScreen
import com.albertomier.cv_management.company.ui.screens.CompanyDetailScreen
import com.albertomier.cv_management.company.ui.screens.UpdateInterviewScreen
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.core.utils.Preferences
import com.albertomier.cv_management.main.navigation.Screens
import com.albertomier.cv_management.ui.theme.BaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyDetailActivity : ComponentActivity() {
    private val viewModel: CompanyViewModel by viewModels()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getIntExtra(Preferences.COMPANY_ID, 0)

        with(viewModel) {
            getCompanyById(id)
            getInterviewList(id)
        }

        setContent {
            BaseTheme {
                val navigationController = rememberNavController()

                NavHost(
                    navController = navigationController,
                    startDestination = Screens.CompanyDetail.route,
                ) {
                    composable(Screens.CompanyDetail.route) {
                        CompanyDetailScreen(
                            viewModel,
                            this@CompanyDetailActivity,
                            navigationController
                        )
                    }
                    composable(
                        Screens.UpdateInterviewDetail.route,
                        arguments = listOf(
                            navArgument("id") { type = NavType.IntType },
                            navArgument("date") { type = NavType.StringType },
                            navArgument("hour") { type = NavType.StringType },
                            navArgument("comment") { type = NavType.StringType },
                            navArgument("done") { type = NavType.IntType })
                    ) { backStackEntry ->
                        UpdateInterviewScreen(
                            backStackEntry.arguments?.getInt("id") ?: 0,
                            backStackEntry.arguments?.getString("date") ?: "",
                            backStackEntry.arguments?.getString("hour") ?: "",
                            backStackEntry.arguments?.getString("comment") ?: "",
                            backStackEntry.arguments?.getInt("done") ?: 0,
                            viewModel,
                            this@CompanyDetailActivity
                        )
                    }
                    composable(Screens.AddInterview.route) {
                        AddInterviewScreen(
                            viewModel = viewModel,
                            activity = this@CompanyDetailActivity,
                            navigationController
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        with(viewModel) {
            getCompanyById(id)
            getInterviewList(id)
        }
    }
}