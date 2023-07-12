package com.albertomier.cv_management.profile.ui.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.albertomier.cv_management.profile.ui.screens.ProfileScreen
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel
import com.albertomier.cv_management.ui.theme.BaseTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {
    private val viewModel: ProfileViewModel by viewModels()

    companion object {
        private const val PDF_SELECTION_CODE = 99
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BaseTheme {
                ProfileScreen(viewModel, this@ProfileActivity) {
//                    val file = it?.let { it1 -> File(it1) }
//                    val target = Intent(Intent.ACTION_VIEW)
//                    target.setDataAndType(Uri.fromFile(file), "application/pdf")
//                    target.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//
//                    val intent = Intent.createChooser(target, "Open File")
//                    try {
//                        startActivity(intent)
//                    } catch (e: ActivityNotFoundException) {
//                        e.printStackTrace()
//                    }
                    openPDF(it)
                }
            }
        }
    }

    private fun openPDF(fileUri: Uri) {

    }
}