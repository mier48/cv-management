package com.albertomier.cv_management.company.ui.screens

import androidx.compose.runtime.Composable
import com.albertomier.cv_management.company.domain.model.InterviewItem
import com.albertomier.cv_management.company.ui.view.CompanyDetailActivity
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.main.components.ItemInterview

@Composable
fun UpdateInterviewScreen(
    id: Int,
    date: String,
    hour: String,
    comment: String,
    done: Int,
    viewModel: CompanyViewModel,
    companyDetailActivity: CompanyDetailActivity
) {

    ItemInterview(InterviewItem(id, 0, date, hour, comment, done, "", "")) { _, _, _, _, _ -> }
}