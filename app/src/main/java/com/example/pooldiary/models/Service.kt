package com.example.pooldiary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "user_name") var user_name: String,
    @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "pool_status") var pool_status: String,
    @ColumnInfo(name = "ph_value") var ph_value: String,
    @ColumnInfo(name = "chemistry_available") var chemistry_available: String,
    @ColumnInfo(name = "chemistry_used") var chemistry_used: String,
    @ColumnInfo(name = "datetime") var datetime: LocalDateTime,
    @ColumnInfo(name = "is_paid") var is_paid: Boolean,
    @ColumnInfo(name = "price") var price: Int
)