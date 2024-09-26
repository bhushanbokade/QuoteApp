package com.bhupendra.quote.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bhupendra.quote.R
import com.bhupendra.quote.viewmodels.DetailViewModel

@Preview
@Composable
fun DetailScreen() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val quotes = detailViewModel.quotes.collectAsState()
    if (quotes.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.loading), style = MaterialTheme.typography.h3)
        }
    } else {
        LazyColumn(content = {
            items(quotes.value) {
                QuoteListItem(tweet = it.text)
            }
        })
    }
}

@Composable
fun QuoteListItem(tweet: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.large_padding)),
        border = BorderStroke(
            dimensionResource(id = R.dimen.border), colorResource(id = R.color.lite_gray)
        ),
        content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.large_padding)),
                style = MaterialTheme.typography.body2
            )
        })
}