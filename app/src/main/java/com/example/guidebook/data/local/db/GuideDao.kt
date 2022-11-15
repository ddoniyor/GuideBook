package com.example.guidebook.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.guidebook.data.local.model.GuideEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GuideDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGuide(guide: GuideEntity)

    @Query("SELECT * FROM ${GuideEntity.TABLE_NAME} ORDER BY id ASC")
    fun getGuides(): Flow<List<GuideEntity>>
}