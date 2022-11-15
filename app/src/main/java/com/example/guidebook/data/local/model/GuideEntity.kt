package com.example.guidebook.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = GuideEntity.TABLE_NAME )
data class GuideEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val endDate: String,
    val icon: String,
    val name: String,
    val objType: String,
    val startDate: String,
    val url: String
){
    companion object {
        const val TABLE_NAME = "guides"
    }
}
