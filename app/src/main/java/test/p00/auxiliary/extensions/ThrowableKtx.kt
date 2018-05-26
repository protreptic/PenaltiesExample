package test.p00.auxiliary.extensions

import retrofit2.HttpException
import test.p00.data.remote.authorize.Authorize
import test.p00.data.remote.authorize.AuthorizeError
import test.p00.data.model.Error

val defaultError = AuthorizeError(0, "Что-то пошло не так...", true)

/**
 * Created by Peter Bukhal on 5/20/18.
 */
fun Throwable.get(): Error =
    when (this) {
        is HttpException -> {
            try {
                Authorize.gson.fromJson(response().errorBody()?.string(), Error::class.java)
            } catch (e: Exception) {
                defaultError
            }
        }
        else -> defaultError
    }