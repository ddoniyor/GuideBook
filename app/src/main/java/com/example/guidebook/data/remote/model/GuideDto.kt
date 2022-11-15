package com.example.guidebook.data.remote.model

import com.example.guidebook.data.local.model.GuideEntity
import com.example.guidebook.domain.model.Guide

data class GuideDto(
    val endDate: String,
    val icon: String,
    val loginRequired: Boolean,
    val name: String,
    val objType: String,
    val startDate: String,
    val url: String,
    val venue: VenueDto
)

fun GuideDto.toGuide():Guide{
    return Guide(
        endDate = endDate,
        icon = icon,
        loginRequired= loginRequired,
        name = name,
        objType = objType,
        startDate = startDate,
        url = url
    )
}
fun GuideDto.toGuideEntity():GuideEntity{
    return GuideEntity(
        endDate = endDate,
        icon = icon,
        name = name,
        objType = objType,
        startDate = startDate,
        url = url
    )
}