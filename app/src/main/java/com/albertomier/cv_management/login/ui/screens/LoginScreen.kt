@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertomier.cv_management.login.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.albertomier.cv_management.R
import com.albertomier.cv_management.core.extensions.Height
import com.albertomier.cv_management.core.extensions.Width
import com.albertomier.cv_management.core.extensions.assetsToBitmap
import com.albertomier.cv_management.core.extensions.radius
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.utils.ic_background
import com.albertomier.cv_management.core.utils.ic_facebook
import com.albertomier.cv_management.core.utils.ic_google
import com.albertomier.cv_management.core.utils.ic_logo
import com.albertomier.cv_management.login.ui.viewmodel.LoginViewModel
import com.albertomier.cv_management.main.base.ShowProgressIndicator
import com.albertomier.cv_management.main.view.MainActivity
import com.albertomier.cv_management.ui.theme.boldTextStyle
import com.albertomier.cv_management.ui.theme.primaryTextStyle
import com.albertomier.cv_management.ui.theme.secondaryTextStyle

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navigationController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        Body(loginViewModel, navigationController)
    }
}

@Composable
fun Body(loginViewModel: LoginViewModel, navigationController: NavHostController) {
    val context = LocalContext.current
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnabled.observeAsState(initial = false)

    Scaffold(
        content = {
            Box(
                modifier = Modifier.padding(
                    start = it.calculateStartPadding(LayoutDirection.Ltr),
                    top = it.calculateTopPadding(),
                    end = it.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = it.calculateBottomPadding()
                )
            ) {
                Image(
                    modifier = Modifier
                        .height(200.dp)
                        .align(Alignment.TopEnd)
                        .fillMaxSize()
                        .rotate(180f),
                    bitmap = context.assetsToBitmap(ic_background)!!.asImageBitmap(),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(0.3f)),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                )
                Image(
                    modifier = Modifier
                        .height(200.dp)
                        .align(Alignment.BottomEnd)
                        .fillMaxSize()
                        .rotate(180f),
                    bitmap = context.assetsToBitmap(ic_background)!!.asImageBitmap(),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(0.3f)),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                )
                LoginForm(
                    context,
                    email,
                    password,
                    isLoginEnable,
                    loginViewModel,
                    navigationController
                )
            }
        }
    )
}

@Composable
fun LoginForm(
    context: Context,
    email: String,
    password: String,
    isLoginEnable: Boolean,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)

    val status: ApiResponseStatus<Any> by loginViewModel.status.observeAsState(initial = ApiResponseStatus.Loading())

    when (status) {
        is ApiResponseStatus.Error -> {
            ShowProgressIndicator()
            Toast.makeText(
                context,
                stringResource(id = (status as ApiResponseStatus.Error<Any>).messageId),
                Toast.LENGTH_LONG
            ).show()
        }
        is ApiResponseStatus.Loading -> ShowProgressIndicator()
        is ApiResponseStatus.Success -> {
            activity?.finish()
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        item {
            Text(
                stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                style = boldTextStyle(fontSize = 18.sp)
            )
            24.Height()
            Box(contentAlignment = Alignment.TopCenter) {
                Card(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                        top = 50.dp
                    ),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = 24.radius(),
                    content = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 50.dp,
                                    bottom = 36.dp
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.Start,
                            ) {
                                EmailTextField(
                                    context,
                                    email,
                                    password,
                                    isLoginEnable,
                                    loginViewModel
                                )
                                8.Height()
                                PasswordTextField(
                                    context,
                                    email,
                                    password,
                                    isLoginEnable,
                                    loginViewModel
                                )
                            }
                            16.Height()
                            Text(
                                stringResource(id = R.string.forgot_password),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = primaryTextStyle(),
                                textAlign = TextAlign.End
                            )
                            16.Height()
                            Button(
                                onClick = {
                                    loginViewModel.makeLogin(email, password)
                                },
                                shape = 24.radius(),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                enabled = isLoginEnable,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .padding(
                                        start = 32.dp,
                                        end = 32.dp,
                                        top = 16.dp,
                                        bottom = 16.dp
                                    )
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        start = 16.dp,
                                        top = 4.dp,
                                        bottom = 4.dp,
                                        end = 16.dp
                                    ),
                                    text = stringResource(id = R.string.btn_login),
                                    style = boldTextStyle(color = Color.White)
                                )
                            }
                            24.Height()
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Divider(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(1.dp)
                                )
                                8.Width()
                                Text(
                                    stringResource(id = R.string.separator_or),
                                    style = secondaryTextStyle()
                                )
                                8.Width()
                                Divider(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(1.dp)
                                )

                            }
                            32.Height()
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Card(
                                    elevation = CardDefaults.cardElevation(2.dp),
                                    shape = 16.radius(),
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(60.dp),
                                    content = {
                                        Image(
                                            bitmap = context.assetsToBitmap(ic_facebook)!!
                                                .asImageBitmap(),
                                            contentScale = ContentScale.Fit,
                                            contentDescription = "",
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    },
                                )
                                24.Width()
                                Card(
                                    elevation = CardDefaults.cardElevation(2.dp),
                                    shape = 16.radius(),
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(60.dp),
                                    content = {
                                        Image(
                                            bitmap = context.assetsToBitmap(ic_google)!!
                                                .asImageBitmap(),
                                            contentScale = ContentScale.Fit,
                                            contentDescription = "",
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    },
                                )
                            }
                            28.Height()
                            Text(modifier = Modifier.clickable {
//                                navigationController.navigate(
//                                    Routes.Register.id
//                                )
                            }, text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                ) {
                                    append(stringResource(id = R.string.info_not_account))
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 14.sp
                                    )
                                )
                                {
                                    append(" " + stringResource(id = R.string.info_not_account_register))
                                }
                            })
                        }
                    },
                )
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = 16.radius(),
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp),
                    content = {
                        Image(
                            bitmap = context.assetsToBitmap(ic_logo)!!
                                .asImageBitmap(),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                            contentScale = ContentScale.FillHeight,
                            contentDescription = "",
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                )
            }
            16.Height()
        }
    }
}

@Composable
fun PasswordTextField(
    context: Context,
    email: String,
    password: String,
    loginEnable: Boolean,
    loginViewModel: LoginViewModel
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        shape = 16.radius(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray.copy(0.1f),
        ),
        leadingIcon = {
            Card(
                elevation = CardDefaults.cardElevation(1.dp),
                shape = 8.radius(),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(
                        bottom = 8.dp,
                        top = 8.dp,
                        start = 12.dp,
                        end = 8.dp
                    ),
                content = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Gray.copy(0.1f))
                            .size(18.dp)
                            .padding(8.dp)
                    )
                }
            )
        },
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = image,
                    contentDescription = "Show password",
                    tint = MaterialTheme.colorScheme.primary.copy(
                        0.2f
                    ),
                    modifier = Modifier
                        .size(18.dp)
                )
            }
        },
        textStyle = primaryTextStyle(),
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = {
            Text(
                text = stringResource(id = R.string.password_hint),
                style = secondaryTextStyle()
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.password_hint),
                style = secondaryTextStyle(fontSize = 16.sp)
            )
        },
        onValueChange = {
            loginViewModel.onLoginChanged(
                email = email,
                password = it
            )
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun EmailTextField(
    context: Context,
    email: String,
    password: String,
    isLoginEnable: Boolean,
    loginViewModel: LoginViewModel
) {
    OutlinedTextField(
        value = email,
        shape = 16.radius(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray.copy(0.1f),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Card(
                elevation = CardDefaults.cardElevation(1.dp),
                shape = 8.radius(),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(
                        bottom = 8.dp,
                        top = 8.dp,
                        start = 12.dp,
                        end = 8.dp
                    ),
                content = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Gray.copy(0.1f))
                            .size(18.dp)
                            .padding(8.dp)

                    )
                }
            )
        },
        textStyle = primaryTextStyle(),
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = stringResource(id = R.string.email_hint),
                style = secondaryTextStyle()
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.email_hint),
                style = secondaryTextStyle(fontSize = 16.sp)
            )
        },
        onValueChange = {
            loginViewModel.onLoginChanged(
                email = it,
                password = password
            )
        }
    )
}