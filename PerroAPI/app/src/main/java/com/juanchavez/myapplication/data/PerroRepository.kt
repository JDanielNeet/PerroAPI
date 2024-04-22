package com.juanchavez.myapplication.data

import retrofit2.Call
import com.juanchavez.myapplication.data.remote.PerroApi
import com.juanchavez.myapplication.data.remote.model.PerroDetailDto
import com.juanchavez.myapplication.data.remote.model.PerroDto
import retrofit2.Retrofit

class PerroRepository (private val retrofit: Retrofit){

    private val perroApi = retrofit.create(PerroApi::class.java)

    fun getPerros(url: String?): Call<List<PerroDto>> = perroApi.getPerros(url)

    fun getPerroDetail(id: String?): Call<PerroDetailDto> = perroApi.getPerroDetail(id)

    //fun getPerroDetailApiary(id: String?): Call<PerroDetailDto> = perroApi.getGameDetailApiary(id)

}