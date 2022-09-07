package com.asha.playerlist

import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.Call

interface MyInterface {
    @POST("oauth2/v2.0/token")
    @FormUrlEncoded

    /*fun getToken(@Header("Content-Type") ContentType:String,
                 @Query("client_id") clientid:String,
                 @Query("client_secret") clientsecret:String,
                 @Query("grant_type") granttype:String,
                 @Query("Scope") Scope:String,):Call<AccessToken>*/
    fun getToken(@Field("client_id") clientid:String,
                 @Field("client_secret") clientsecret:String,
                 @Field("grant_type") granttype:String,
                 @Field("Scope")Scope:String,
                 @Header("content-type") contenttype:String ):Call<AccessToken>

}