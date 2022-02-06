package com.example.webunihw_eatingplaces.network

import com.example.webunihw_eatingplaces.BuildConfig
import com.example.webunihw_eatingplaces.network.apis.AuthAPI
import com.example.webunihw_eatingplaces.network.apis.PlacesAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    const val MainServer = "https://eteto.nebet.hu"

    //by lazy = csak akkor fog példányosodni, ha egyszer valaki már használta
    val retrofitClient: Retrofit.Builder by lazy {
        val levelType: HttpLoggingInterceptor.Level =
            if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okHttpClient = OkHttpClient.Builder()
        //logoló interceptort is adunk hozzá
        okHttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(MainServer)
            .client(okHttpClient.build())
            .addConverterFactory(MoshiConverterFactory.create())
    }

    //regisztráció és login apik
    val authApiInterface: AuthAPI by lazy {
        retrofitClient
            .build()
            .create(AuthAPI::class.java)
    }

    //helyek listája, hely feltöltése, egy hely adatainak lekérése
    val placesApiInterface: PlacesAPI by lazy {
        retrofitClient
            .build()
            .create(PlacesAPI::class.java)
    }

}