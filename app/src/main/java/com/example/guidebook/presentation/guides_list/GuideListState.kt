package com.example.guidebook.presentation.guides_list

import com.example.guidebook.domain.model.Guide

data class GuideListState(
    val isLoading:Boolean =false,
    val isRefreshing:Boolean = false,
    val guides:List<Guide> = emptyList(),
    val error:String = ""
)