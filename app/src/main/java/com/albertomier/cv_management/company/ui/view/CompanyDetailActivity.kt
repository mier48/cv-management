package com.albertomier.cv_management.company.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.albertomier.cv_management.company.ui.screens.CompanyDetailScreen
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.core.utils.Preferences
import com.albertomier.cv_management.ui.theme.BaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyDetailActivity : ComponentActivity() {
    private val viewModel: CompanyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra(Preferences.COMPANY_ID, 0)

        viewModel.getCompanyById(id)

        setContent {
            BaseTheme {
                CompanyDetailScreen(viewModel, this@CompanyDetailActivity)
            }
        }
    }
}