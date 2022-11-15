package com.example.guidebook.presentation.guides_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guidebook.presentation.GuideBookScreen
import com.example.guidebook.presentation.guides_list.components.GuideItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson
import okio.ByteString.Companion.encode
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun GuidesList(
    navController: NavController,
    viewModel: GuideListViewModel = hiltViewModel()
) {
    SwipeRefreshCompose(viewModel = viewModel, navController = navController)
}

@SuppressLint("ResourceType")
@Composable
fun SwipeRefreshCompose(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: GuideListViewModel,
) {
    val state = viewModel.stateApi.value

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
        onRefresh = {viewModel.getGuidesApi(isRefreshing = true)}) {

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.guides) { guide ->
                    GuideItem(guide = guide,
                        onCardClick = {
                            val encodedUrl = URLEncoder.encode(guide.url, StandardCharsets.UTF_8.toString())
                            navController.navigate(GuideBookScreen.Detail.screen_route+"/$encodedUrl")
                        }
                    )
                }
            }
            if (state.error.isNotBlank())  {
                Text(text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}