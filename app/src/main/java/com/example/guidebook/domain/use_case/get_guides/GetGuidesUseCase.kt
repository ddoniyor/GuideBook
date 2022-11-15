package com.example.guidebook.domain.use_case.get_guides

import com.example.guidebook.common.Resource
import com.example.guidebook.data.remote.model.toGuide
import com.example.guidebook.domain.model.Guide
import com.example.guidebook.domain.model.Guides
import com.example.guidebook.domain.repository.GuideBookRepository
import com.example.guidebook.utils.ApiLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGuidesUseCase @Inject constructor(
    private val repository: GuideBookRepository
) {
    private val TAG = "GetGuidesUseCase"
    operator fun invoke() : Flow<Resource<List<Guide>>> = flow{
        try {
            emit(Resource.Loading<List<Guide>>())
            val guides = repository.getGuidesApi().data.map { it.toGuide() }
            emit(Resource.Success<List<Guide>>(guides))
            ApiLogger.isSuccess(TAG,guides)
        }catch (e: HttpException){
            emit(Resource.Error<List<Guide>>(e.localizedMessage?:"An unexpected error occurred."))
            ApiLogger.isUnSuccess(TAG, e.localizedMessage?:"An unexpected error occurred.")
        }catch (e: IOException){
            emit(Resource.Error<List<Guide>>("Could not reach the server. Check internet connection."))
            ApiLogger.isFailure(TAG,"${e.message}")
        }
    }
}