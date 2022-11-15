package com.example.guidebook.data.repository

import com.example.guidebook.data.local.db.AppDatabase
import com.example.guidebook.data.local.model.GuideEntity
import com.example.guidebook.data.remote.api.GuideBookApi
import com.example.guidebook.data.remote.model.GuideDto
import com.example.guidebook.data.remote.model.GuidesDto
import com.example.guidebook.data.remote.model.toGuide
import com.example.guidebook.data.remote.model.toGuideEntity
import com.example.guidebook.domain.model.Guides
import com.example.guidebook.domain.repository.GuideBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GuideBookRepositoryImpl @Inject constructor(
    private val api: GuideBookApi,
    private val db: AppDatabase
) : GuideBookRepository {
    override suspend fun getGuidesApi(): GuidesDto {
        val guides =  api.getGuides()
        coroutineScope {
            launch {
                withContext(Dispatchers.IO) {
                    for (guide in guides.data.map {it.toGuideEntity()}){
                        db.guideDao().addGuide(guide)
                    }
                }
            }
        }

        return guides
    }

    override suspend fun getGuidesDb(): Flow<List<GuideEntity>> {
        return db.guideDao().getGuides()
    }

}