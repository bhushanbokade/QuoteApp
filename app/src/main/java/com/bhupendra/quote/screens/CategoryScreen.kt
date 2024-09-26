package com.bhupendra.quote.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bhupendra.quote.R
import com.bhupendra.quote.utils.categoryList

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.medium_padding)),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        items(categoryList) {
            CategoryItem(category = it, onClick)
        }
    }
}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.small_padding))
            .clickable {
                onClick(category)
            }
            .size(dimensionResource(id = R.dimen.card_size))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium_padding)))
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .border(dimensionResource(id = R.dimen.border), colorResource(id = R.color.lite_gray)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            fontSize = dimensionResource(id = R.dimen.cat_text_size).value.sp,
            color = Color.Black,
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.no_dp),
                dimensionResource(id = R.dimen.x_large_padding)
            ),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun CategoryPreview() {
    CategoryScreen(onClick = {})
}










