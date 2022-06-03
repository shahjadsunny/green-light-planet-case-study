package com.greenlightplanet.casestudy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sales_area")
data class SalesArea(

    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo(name = "area")
    val area: String,

)