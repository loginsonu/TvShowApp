package com.example.tvshowapp.util

import java.io.InputStreamReader

object DataUtil {
    fun getRawDataFromFile(fileName: String): String {
        val inputStream = DataUtil::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}