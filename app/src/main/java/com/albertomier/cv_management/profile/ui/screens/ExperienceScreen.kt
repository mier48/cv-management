package com.albertomier.cv_management.profile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.main.base.FabButton
import com.albertomier.cv_management.main.base.Title
import com.albertomier.cv_management.main.data.SheetContentState
import com.albertomier.cv_management.profile.data.Experience
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExperienceScreen(viewModel: ProfileViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val sheetStateContent by viewModel.sheetStateContent.collectAsState()

    val listOfExperience = remember { viewModel.experienceList }

    ModalBottomSheetLayout(
        scrimColor = Color.Black.copy(alpha = 0.6f),
        sheetState = modalBottomSheetState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetContent = {
            when (sheetStateContent) {
                SheetContentState.ADD -> {
                    AddExperienceSheetContent(
                        context = context,
                        mainViewModel = viewModel,
                        modalBottomSheetState = modalBottomSheetState,
                        scope = scope,
                        viewModel = viewModel,
                        onAddClicked = {
//                            val experience = Experience(
//                                employer = mainViewModel.employer.value,
//                                position = mainViewModel.position.value,
//                                fromYear = mainViewModel.yearOfStart.value,
//                                stillWorking = mainViewModel.stillWorking.value,
//                                endYear = if (mainViewModel.stillWorking.value) "" else mainViewModel.yearOfEnding.value,
//                                comment = mainViewModel.experienceComment.value
//                            )
//                            mainViewModel.addExperienceItem(experience)
//                            scope.launch {
//                                modalBottomSheetState.hide()
//                            }
//                            mainViewModel.resetExperienceFields()
                        }
                    )
                }
                SheetContentState.UPDATE -> {
                    UpdateExperienceSheetContent(
                        context = context,
                        mainViewModel = viewModel,
                        onSaveClicked = {
//                            mainViewModel.updateExperienceItem(
//                                index = listOfExperience.indexOf(mainViewModel.selectedExperienceItem.value),
//                                experience = it
//                            )
//                            scope.launch {
//                                modalBottomSheetState.hide()
//                            }
//                            mainViewModel.resetExperienceFields()
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FabButton(text = "Añadir", isVisibleBecauseOfScrolling = true, onItemClick = {
                    viewModel.setSheetStateContent(SheetContentState.ADD)
                    scope.launch {
                        modalBottomSheetState.show()
                    }
                })
            }
        ) {
            Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                when {
                    listOfExperience.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Title(title = "Nothing to show here yet")
                        }
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            items(listOfExperience) { experience ->

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExperienceInfoItem(
    experienceInfo: Experience,
    onItemClicked: (experienceInfo: Experience) -> Unit,
    enableDeleteAction: Boolean = false,
    deleteEducationalInfo: (experienceInfo: Experience) -> Unit
) {
    val delete = SwipeAction(
        onSwipe = {
            deleteEducationalInfo(experienceInfo)
        },
        icon = {
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete icon",
                tint = Color.White
            )
        },
        background = MaterialTheme.colors.error    )
    SwipeableActionsBox(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        swipeThreshold = 120.dp,
        endActions = if (enableDeleteAction) listOf(delete) else listOf()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = {
                    onItemClicked(experienceInfo)
                }),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 8.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Empresa: ${experienceInfo.company}",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Position: ${experienceInfo.jobTitle}",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Year of starting: ${experienceInfo.startDate}",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = if (experienceInfo.stillWorking) "Still working" else "Year of ending: ${experienceInfo.endDate}",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = experienceInfo.description,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}