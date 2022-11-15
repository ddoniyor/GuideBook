package com.example.guidebook.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.guidebook.data.local.db.AppDatabase.Companion.DB_VERSION
import com.example.guidebook.data.local.model.GuideEntity

@Database(entities = [GuideEntity::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
        const val NAME = "guideBook.db"
    }
    abstract fun guideDao() : GuideDao
}