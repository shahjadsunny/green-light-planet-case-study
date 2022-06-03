package com.greenlightplanet.casestudy.utility

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}