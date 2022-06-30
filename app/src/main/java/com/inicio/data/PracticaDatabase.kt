package com.inicio.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.inicio.model.Practica

@Database(entities = [Practica::class], version = 1, exportSchema = false)
abstract class PracticaDatabase: RoomDatabase() {
    abstract  fun PracticaDao() : PracticaDao

    companion object {

        @Volatile
        private var INSTANCE: PracticaDatabase? = null

        fun getDataBase(context: android.content.Context) : PracticaDatabase {
            val temp = INSTANCE
            if (temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PracticaDatabase::class.java,
                    "practica_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}