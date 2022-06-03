package com.greenlightplanet.casestudy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sales_region")
data class SalesRegion(

    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo(name = "region")
    val region: String,


)