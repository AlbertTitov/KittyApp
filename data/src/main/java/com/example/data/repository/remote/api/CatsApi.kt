package com.example.data.repository.remote.api

import com.example.data.repository.remote.model.CatDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface CatsApi {

    /**
     *   Получение списка моделей с информацией о котах, где
     *   limit - количество котов;
     *   page - номер кота, начиная с которого запрашивается список котов;
     *   order - порядок сортировки котов, значения - "DESC"(по убыванию), "ASC"(по возрастанию), "RAND"(произвольный)
     *
     * */


    /**
     *      Прошу обратить внимание, что данный хост, https://api.thecatapi.com/ работает (по крайней мере у меня) только с vpn,
     *      а иначе - java.net.UnknownHostException: Unable to resolve host "api.thecatapi.com": No address associated with hostname
     **/
    @GET("v1/images/search")
    fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("direction") direction: String,
    ): Single<List<CatDto>>

}