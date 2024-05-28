package com.example.pooldiary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")

data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "phone_number") var phone_number: String,
    @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "coordinates") var coordinates: String,
    @ColumnInfo(name = "default_service_price") var default_service_price: Int
)
