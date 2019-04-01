package br.com.stant.stant_android_occurrences.services

import com.angelomelo.soluevochallenge.service.CustomInterceptorRequest
import com.angelomelo.soluevochallenge.service.NullOnEmptyConverterFactory
import com.angelomelo.soluevochallenge.service.TokenAuthenticator
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

class ApiDataSource  {

    companion object {

        fun <S> createService(serviceClass: Class<S>): S {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)

            httpClient.addInterceptor(CustomInterceptorRequest())
            httpClient.authenticator(TokenAuthenticator())
            httpClient.addInterceptor(loggingInterceptor)

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://159.65.244.68/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(NullOnEmptyConverterFactory())
                .client(httpClient.build())
                .build()

            return retrofit.create(serviceClass)
        }

    }


}
