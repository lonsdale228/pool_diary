package com.example.pooldiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pooldiary.database.data.ServiceDao
import com.example.pooldiary.database.data.UserDao
import com.example.pooldiary.models.Service
import com.example.pooldiary.models.User

@Database(entities = [User::class, Service::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun serviceDao(): ServiceDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}