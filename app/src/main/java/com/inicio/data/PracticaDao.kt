package com.inicio.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inicio.model.Practica

@Dao
interface PracticaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEstado(practica: Practica)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  updateEstado(practica: Practica)

    @Delete
    suspend fun deletetEstado(practica: Practica)

    @Query("SELECT * FROM ESTADO")
    fun getAllData() : LiveData<List<Practica>>

}