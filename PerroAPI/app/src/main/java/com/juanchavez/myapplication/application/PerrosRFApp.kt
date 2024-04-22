package com.juanchavez.myapplication.application

import android.app.Application
import com.juanchavez.myapplication.data.PerroRepository
import com.juanchavez.myapplication.data.remote.RetroFitHelper

class PerrosRFApp : Application(){

    private val retrofit by lazy {
        RetroFitHelper().getRetroFit()
    }

    val repository by lazy {
        PerroRepository(retrofit)
    }

}