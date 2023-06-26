package com.albertomier.cv_management.company.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.company.domain.model.InterviewItem
import com.albertomier.cv_management.company.ui.view.CompanyDetailActivity
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.core.extensions.Height
import com.albertomier.cv_management.main.base.FabButton
import com.albertomier.cv_management.main.base.TopAppBarReturn
import com.albertomier.cv_management.main.components.ItemCompany
import com.albertomier.cv_management.main.components.ItemInterview
import com.albertomier.cv_management.main.navigation.Screens
import com.albertomier.cv_management.ui.theme.Typography

@Composable
fun CompanyDetailScreen(
    viewModel: CompanyViewModel,
    activity: CompanyDetailActivity,
    navController: NavHostController,
) {
    val listState = rememberLazyListState()
    val fabVisibility by derivedStateOf {
        listState.firstVisibleItemIndex == 0
    }

    val item: CompanyItem by viewModel.companyItem.observeAsState(initial = CompanyItem())
    val interviewList: List<InterviewItem> by viewModel.interviewList.observeAsState(initial = emptyList())

    var visible by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarReturn(
                title = item.name,
                activity = activity,
                tint = MaterialTheme.colorScheme.surface,
                color = MaterialTheme.colorScheme.primary
            )
        },
        floatingActionButton = {
            FabButton(text = "Añadir entrevista", isVisibleBecauseOfScrolling = fabVisibility) {
                navController.navigate(Screens.AddInterview.route)
            }
        }
    ) {
        Surface(
            shadowElevation = 9.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Column {
                    Row(modifier = Modifier.clickable { visible = !visible }) {
                        ItemCompany(item = item, showDescription = true, onItemSelected = {})
                    }
                    if (visible) {
                        16.Height()
                        if (interviewList.isNotEmpty()) {
                            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                                Text(text = "Entrevistas", style = Typography.titleLarge)
                            }
                        }
                    }
                }
                LazyColumn(state = listState) {
                    items(interviewList) { item ->
                        Row {
                            ItemInterview(item, onItemSelected = { id, date, hour, comment, done ->
                                navController.navigate(
                                    Screens.UpdateInterviewDetail.createRoute(
                                        id,
                                        date,
                                        hour,
                                        comment,
                                        done
                                    )
                                )
                            })
                        }
                    }
                }
            }
        }
    }
}