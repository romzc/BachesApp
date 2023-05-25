package com.romzc.bachesapp.data.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.romzc.bachesapp.data.model.UserDao

@Database(
    entities = [UserEntity::class],
    version = 3
)
abstract  class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    //abstract fun potholeDao(): PotholeDao
    //abstract fun severityDao(): SeverityDao



    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database-name"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }
                return instance
            }
        }
    }
}