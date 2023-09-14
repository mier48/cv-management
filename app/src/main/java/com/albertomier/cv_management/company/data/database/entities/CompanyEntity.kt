package com.albertomier.cv_management.company.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertomier.cv_management.company.domain.model.CompanyItem

@Entity(tableName = "companies")
data class CompanyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
)

fun CompanyItem.toDatabase() = CompanyEntity(id)