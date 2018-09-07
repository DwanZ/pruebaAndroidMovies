package com.rappi.movies.utils.exceptions
import android.content.Context
import com.rappi.movies.BuildConfig
import retrofit2.HttpException

import java.net.ConnectException
import java.util.concurrent.TimeoutException

class ErrorMessageFactory private constructor(private val mContext: Context) {

    fun create(exception: Throwable): String {

        if (BuildConfig.DEBUG_ENABLED.toBoolean()) exception.printStackTrace()

        return when (exception) {
            is ConnectException -> "Conection error"
            is TimeoutException -> "Conection error"
            is HttpException -> "Conection error"
            is java.net.UnknownHostException -> "Conection error"
            else -> "An unexpected error has occurred"
        }
    }
    companion object {
        fun Context.errorMessageFactory(): ErrorMessageFactory = ErrorMessageFactory(applicationContext)
    }

}