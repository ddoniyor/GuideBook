package com.example.guidebook.data.remote.api

import com.example.guidebook.data.remote.model.GuidesDto
import retrofit2.http.GET

interface GuideBookApi {
    @GET("/service/v2/upcomingGuides")
    suspend fun getGuides():GuidesDto
}