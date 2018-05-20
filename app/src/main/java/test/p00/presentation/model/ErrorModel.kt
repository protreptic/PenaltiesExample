package test.p00.presentation.model

import test.p00.data.model.Error

/**
 * Created by Peter Bukhal on 5/20/18.
 */
data class ErrorModel(val code: Int, val message: String) {

    object Mapper {

        fun map(error: Error) =
                ErrorModel(error.code, error.message)

    }

}