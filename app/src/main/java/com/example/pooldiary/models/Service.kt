package com.example.pooldiary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "pool_status") var pool_status: String,
    @ColumnInfo(name = "chemistry_available") var chemistry_available: String,
    @ColumnInfo(name = "chemistry_used") var chemistry_used: String,
    @ColumnInfo(name = "datetime") var datetime: String
)