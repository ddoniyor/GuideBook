package com.example.guidebook.domain.repository

import com.example.guidebook.data.local.model.GuideEntity
import com.example.guidebook.data.remote.model.GuidesDto
import kotlinx.coroutines.flow.Flow

interface GuideBookRepository {

    suspend fun getGuidesApi(): GuidesDto

    suspend fun getGuidesDb(): Flow<List<GuideEntity>>
}