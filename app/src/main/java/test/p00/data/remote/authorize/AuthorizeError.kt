package test.p00.data.remote.authorize

import test.p00.data.model.Error

/**
 * Created by Peter Bukhal on 5/20/18.
 */
class AuthorizeError(code: Int, message: String, forUser: Boolean):
        Error(code, message, forUser)