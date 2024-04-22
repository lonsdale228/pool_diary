package com.example.pooldiary.models

import androidx.room.Embedded
import androidx.room.Relation

data class ServiceWithUser(
    @Embedded val service: Service,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
        entity = User::class
    )
    val user: User
)