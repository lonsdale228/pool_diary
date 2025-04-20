package com.example.pooldiary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "chemistry")
data class Chemistry(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "chemistry_name") var chemistry_name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "guide") var guide: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "weights_prices") var weights_prices: String,
    @ColumnInfo(name = "barcode") var barcode: String,
    @ColumnInfo(name = "datetime") var datetime: LocalDateTime,
    @ColumnInfo(name = "note") var note: String,
)