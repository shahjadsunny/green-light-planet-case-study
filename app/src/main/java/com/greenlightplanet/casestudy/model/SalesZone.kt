package com.greenlightplanet.casestudy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sales_zone")
data class SalesZone(

    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "zone")
    val zone: String
)