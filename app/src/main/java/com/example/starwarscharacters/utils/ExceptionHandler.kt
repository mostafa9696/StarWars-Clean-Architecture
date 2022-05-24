package com.example.starwarscharacters.utils

import androidx.annotation.StringRes
import com.example.starwarscharacters.R
import java.net.UnknownHostException

internal object ExceptionHandler {

    @StringRes
    fun parse(t: Throwable): Int {
        return when (t) {
            is UnknownHostException -> R.string.check_internet_connection
            else -> R.string.error_occured
        }
    }

}
