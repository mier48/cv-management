package com.albertomier.cv_management.company.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.albertomier.cv_management.company.ui.view.CompanyDetailActivity
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.main.base.TopAppBarReturn
import com.albertomier.cv_management.main.components.DefaultButton
import com.albertomier.cv_management.main.components.TextFieldRounded
import java.util.*

@Composable
fun AddInterviewScreen(
    viewModel: CompanyViewModel,
    activity: CompanyDetailActivity,
    navigationController: NavHostController
) {
    val mContext = LocalContext.current

    val companyName: String by viewModel.companyName.observeAsState(initial = "")
    val companyPhone: String by viewModel.companyPhone.observeAsState(initial = "")
    val companyContactName: String by viewModel.companyContactName.observeAsState(initial = "")
    val companyContactPhone: String by viewModel.companyContactPhone.observeAsState(initial = "")
    val isAddCompanyEnabled: Boolean by viewModel.isAddCompanyEnabled.observeAsState(initial = false)

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }
    val mDate = remember { mutableStateOf("") }

    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        mContext, { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarReturn(
                title = "Añadir Entrevista",
                activity = activity,
                tint = MaterialTheme.colorScheme.surface,
                color = MaterialTheme.colorScheme.primary,
                navigationController = navigationController
            )
        }
    ) {
        Surface(
            shadowElevation = 9.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextFieldRounded(
                        enabled = false,
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { mDatePickerDialog.show() },
                        value = mDate.value,
                        label = "Fecha",
                        placeholder = "Añade la fecha de la entrevista"
                    ) { value ->

                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldRounded(
                    enabled = false,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { mTimePickerDialog.show() },
                    value = mTime.value,
                    label = "Hora",
                    placeholder = "Añade la hora de la entrevista"
                ) { value ->

                }
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldRounded(
                    value = companyName,
                    label = "Comentario",
                    placeholder = "Añade un comentario de la entrevista",
                    singleLine = false,
                    minLines = 3,
                    maxLines = 6
                ) { value ->

                }
                Spacer(modifier = Modifier.height(16.dp))
                DefaultButton(
                    text = "Añadir Empresa",
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isAddCompanyEnabled,
                    onButtonClick = {
                        navigationController.popBackStack()
                    })
            }
        }
    }
}
