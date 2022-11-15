package com.example.guidebook.presentation.guide_detail

import android.annotation.SuppressLint
import android.net.http.SslError
import android.util.Log
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.guidebook.common.Constants


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GuideWebView(url: String) {
    val mainUrl = Constants.BASE_URL + url
    val redirectedUrl = "https://builder.guidebook.com/g/#/guides/"

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true
            settings.allowContentAccess = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    if (request?.url.toString().startsWith(redirectedUrl)) {
                        view?.loadUrl(request?.url.toString())
                        return true
                    }
                    return false
                }
            }
            loadUrl(mainUrl)
        }
    }, update = {
        it.loadUrl(mainUrl)
    })
}