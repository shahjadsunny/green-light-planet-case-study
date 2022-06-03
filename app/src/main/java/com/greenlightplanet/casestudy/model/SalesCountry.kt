package com.greenlightplanet.casestudy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sales_country")
data class SalesCountry(

    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo(name = "country")
    val country: String,

)