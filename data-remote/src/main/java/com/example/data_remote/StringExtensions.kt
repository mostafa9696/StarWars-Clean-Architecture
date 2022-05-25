package com.example.data_remote


internal fun String.enforceHttps(): String =
    if (!this.contains("https"))
        this.replace("http", "https")
    else this
