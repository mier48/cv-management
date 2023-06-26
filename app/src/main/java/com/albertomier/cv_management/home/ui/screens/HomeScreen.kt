package com.albertomier.cv_management.home.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.home.ui.viewmodel.HomeViewModel
import com.albertomier.cv_management.main.components.ItemCompany

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    listState: LazyListState,
    onItemSelected: (id: Int?) -> Unit
) {
    val context = LocalContext.current
    val status: ApiResponseStatus<Any> by viewModel.status.observeAsState(initial = ApiResponseStatus.Loading())
    val companyList: List<CompanyItem> by viewModel.companyList.observeAsState(initial = emptyList())

//    when (status) {
//        is ApiResponseStatus.Error -> {
//            Toast.makeText(
//                context,
//                stringResource(id = (status as ApiResponseStatus.Error<Any>).messageId),
//                Toast.LENGTH_LONG
//            ).show()
//        }
//        is ApiResponseStatus.Loading -> ShowProgressIndicator()
//        is ApiResponseStatus.Success -> {
//            LazyColumn {
//                items(companyList) { item ->
//                    ItemCompany(item)
//                }
//            }
//        }
//    }
    LazyColumn(state = listState) {
        items(companyList) { item ->
            Row {
                ItemCompany(item = item, onItemSelected = onItemSelected)
            }
        }
    }

}