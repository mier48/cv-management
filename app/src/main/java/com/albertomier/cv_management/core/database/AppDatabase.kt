package com.albertomier.cv_management.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.albertomier.cv_management.company.data.database.Converters
import com.albertomier.cv_management.company.data.database.dao.CompanyDao
import com.albertomier.cv_management.company.data.database.entities.CompanyEntity

@Database(entities = [CompanyEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCompanyDao(): CompanyDao
}