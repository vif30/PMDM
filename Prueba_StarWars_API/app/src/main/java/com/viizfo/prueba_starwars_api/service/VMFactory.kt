package com.viizfo.prueba_starwars_api.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viizfo.prueba_starwars_api.viewmodel.MainViewModel

/*
 * Factoría para pasar múltiples argumentos
 */

class VMFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel no encontrado")
        }
    }
}