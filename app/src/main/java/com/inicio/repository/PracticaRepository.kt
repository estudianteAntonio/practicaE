package com.inicio.repository

import androidx.lifecycle.LiveData
import com.inicio.data.PracticaDao
import com.inicio.model.Practica

class PracticaRepository (private var practicaDao: PracticaDao) {

    suspend fun addEstado(practica: Practica){
        practicaDao.addEstado(practica)
    }

    suspend fun  updateEstado(practica: Practica){
        practicaDao.updateEstado(practica)
    }

    suspend fun deletetEstado(practica: Practica){
        practicaDao.deletetEstado(practica)
    }

    val getAllData : LiveData<List<Practica>> = practicaDao.getAllData()
}