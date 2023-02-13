package com.viizfo.prueba_starwars_api.view

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.viizfo.prueba_starwars_api.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Inflar el archivo XML de las preferencias
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        // Establecer una referencia a este fragmento
        preferenceFragmentCompat = this

        // Obtener una referencia al EditTextPreference "movie_text"
        val movieTextPreference = findPreference<EditTextPreference>("movie_text")
        // Obtener una referencia al Preference "tv_movies"
        val tvMovies = findPreference<Preference>("tv_movies")

        // Establecer un listener de cambio para la preferencia "movie_text"
        movieTextPreference?.setOnPreferenceChangeListener { _, newValue ->
            // Actualizar el summary de la preferencia "tv_movies" con el nuevo valor
            tvMovies?.summary = newValue.toString()
            true
        }

    }

    companion object {
        // Variable estática para hacer referencia a este fragmento
        var preferenceFragmentCompat: PreferenceFragmentCompat? = null
    }
}