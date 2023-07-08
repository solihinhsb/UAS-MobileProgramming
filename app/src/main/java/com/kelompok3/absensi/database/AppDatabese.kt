package com.kelompok3.absensi.database

import androidx.room.Database
import com.kelompok3.absensi.model.ModelDatabase
import androidx.room.RoomDatabase
import com.kelompok3.absensi.database.dao.DatabaseDao

@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}