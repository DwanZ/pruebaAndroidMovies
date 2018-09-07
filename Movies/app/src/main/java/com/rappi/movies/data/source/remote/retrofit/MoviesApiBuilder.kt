package com.rappi.movies.data.source.remote.retrofit

import android.content.Context
import android.content.Intent
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.rappi.movies.BuildConfig
import com.rappi.movies.splash.SplashActivity


class MoviesApiBuilder private constructor(
        private val mOnForbiddenAction: () -> Unit) {

    private var mMoviesApi: MoviesApi? = null

    val moviesApi: MoviesApi
        get() {
            if (mMoviesApi == null) {
                val retrofitBuilder = Retrofit.Builder()
                        .baseUrl(BuildConfig.SERVER_URL)
                        .client(clientInterceptor)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))

                val retrofit = retrofitBuilder.build()

                mMoviesApi = retrofit.create(MoviesApi::class.java)
            }
            return mMoviesApi as MoviesApi
        }

    private // enable http requests and responses log in the debug mode only
            // add header if there is a token in the shared preference
    val clientInterceptor: OkHttpClient
        get() {

            val clientBuilder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            if (BuildConfig.DEBUG_ENABLED.toBoolean()) {
                clientBuilder
                        .addInterceptor(loggingInterceptor)
                        .addNetworkInterceptor(StethoInterceptor())
                        .readTimeout(60, TimeUnit.MINUTES)
                        .writeTimeout(60, TimeUnit.MINUTES)
            } else {
                clientBuilder
                        .addInterceptor(loggingInterceptor)
                        .addNetworkInterceptor(StethoInterceptor())
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
            }
            clientBuilder
                    .addNetworkInterceptor { chain ->
                        var request = chain.request()
                            request = request.newBuilder()
                                    .addHeader(MoviesApi.AUTH_HEADER, "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MjA3NDc4NGFkYTg2YjBlZGYxMDc3YjI1NWI5NzczYSIsInN1YiI6IjViOGY2NzU0YzNhMzY4MzdmZTAwN2E1MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.IFLCbbkE_VLecNwk8FixO0Bqoic1sZplI_ycFoOr69M")
                                    .addHeader("Content-Type", "application/json")
                                    .addHeader("Accept", "application/json")
                                    .build()

                        chain.proceed(request)
                    }
                    .addNetworkInterceptor { chain ->
                        val request = chain.request()
                        val response = chain.proceed(request)
                        if (response.code() == 403) {
                            mOnForbiddenAction()
                        }
                        response
                    }

            return clientBuilder.build()
        }

    private val gson: Gson
        get() = GsonBuilder()
                //.registerTypeAdapter(Date::class.java, DateSerializer())
                .create()

    companion object {

        private var INSTANCE: MoviesApiBuilder? = null
        private var intanceMoviesApi: MoviesApi? = null

        private fun getInstance(
                onForbiddenAction: () -> Unit): MoviesApiBuilder {
            if (INSTANCE == null) {
                INSTANCE = MoviesApiBuilder(
                        onForbiddenAction)
            }
            return INSTANCE as MoviesApiBuilder
        }

        fun Context.moviesApi(): MoviesApi {
            if (intanceMoviesApi == null) {
                val forbiddenAction = {
                    val startActivity = Intent()
                    startActivity.setClass(applicationContext, SplashActivity::class.java)
                    startActivity.action = SplashActivity::class.java.name
                    startActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    startActivity(startActivity)
                }
                intanceMoviesApi = MoviesApiBuilder.getInstance(
                        forbiddenAction
                ).moviesApi
            }
            return intanceMoviesApi!!
        }
    }
}
