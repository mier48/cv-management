package com.albertomier.cv_management.main.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.albertomier.cv_management.company.ui.view.AddCompanyActivity
import com.albertomier.cv_management.company.ui.view.CompanyDetailActivity
import com.albertomier.cv_management.core.utils.Preferences
import com.albertomier.cv_management.home.ui.viewmodel.HomeViewModel
import com.albertomier.cv_management.main.navigation.MyAppNavHost
import com.albertomier.cv_management.ui.theme.BaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BaseTheme {
                MyAppNavHost(
                    viewModel = homeViewModel,
                    onItemSelected = { goToDetailView(it) },
                    onFabButtonClick = {
                        startActivity(Intent(this, AddCompanyActivity::class.java))
                    })
            }
        }
    }

//    private fun onMenuItemClick(resource: Int) {
//        when (resource) {
//            R.drawable.ic_profile -> {
//                startActivity(Intent(this, UserProfileActivity::class.java))
//            }
//            R.drawable.ic_currency -> {
//                startActivity(Intent(this, AddCurrencyActivity::class.java))
//            }
//            R.drawable.ic_bookmark -> {
//                startActivity(Intent(this, BookmarkActivity::class.java))
//            }
//            R.drawable.ic_list -> {
//
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getCompanyList()
    }

    private fun goToDetailView(itemId: Int?) {
        val intent = Intent(this, CompanyDetailActivity::class.java)
        intent.putExtra(Preferences.COMPANY_ID, itemId)
        startActivity(intent)
    }
//
//    private fun goToAuthorView(authorId: Int?) {
//        val intent = Intent(this, AuthorActivity::class.java)
//        intent.putExtra(Preferences.AUTHOR_ID, authorId)
//        startActivity(intent)
//    }
//
//    private fun goToUserView() {
//        startActivity(Intent(this, UserProfileActivity::class.java))
//    }
}
