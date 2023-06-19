package com.albertomier.cv_management.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.albertomier.cv_management.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DefaultImage(
    url: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    alignment: Alignment = Alignment.TopStart,
) {
    AsyncImage(
        model = url,
        contentScale = contentScale,
        alignment = alignment,
        contentDescription = "Post image",
        placeholder = painterResource(R.drawable.ic_app_placeholder),
        modifier = modifier
    )
}

@Composable
fun DefaultImage(
    drawable: Int,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    alignment: Alignment = Alignment.TopStart,
) {
    AsyncImage(
        model = drawable,
        contentScale = contentScale,
        alignment = alignment,
        contentDescription = "Post image",
        placeholder = painterResource(R.drawable.ic_app_placeholder),
        modifier = modifier
    )
}

@Composable
fun LoadGlideImage(
    url: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    alignment: Alignment = Alignment.TopStart,
) {
    GlideImage(
        imageModel = url,
        contentScale = contentScale,
        alignment = alignment,
        contentDescription = "Post image",
        placeHolder = painterResource(R.drawable.ic_app_placeholder),
        modifier = modifier
    )
}