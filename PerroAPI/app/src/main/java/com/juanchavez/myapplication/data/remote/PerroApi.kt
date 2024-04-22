package com.juanchavez.myapplication.data.remote

import com.juanchavez.myapplication.data.remote.model.PerroDetailDto
import com.juanchavez.myapplication.data.remote.model.PerroDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PerroApi {


    @GET
    fun getPerros(
        @Url url: String?
    ): Call<List<PerroDto>>    //Así se llamaría: getGames("cm/games/games_list.php")"



    @GET("razas/{id}")
    fun getPerroDetail(
        @Path("id") id: String?/*,
        @Query("name") name: String?*/
    ): Call<PerroDetailDto>




}