package test.p00.data.remote.authorize

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import test.p00.data.remote.authorize.request.AuthorizeConfirmationRequest
import test.p00.data.remote.authorize.request.AuthorizeRequest
import test.p00.data.remote.authorize.response.AuthorizeConfirmationResponse
import test.p00.data.remote.authorize.response.AuthorizeResponse

/**
 * Created by Peter Bukhal on 5/20/18.
 */
interface Authorize {

    companion object {

        val gson: Gson by lazy { GsonBuilder().create() }

        val api: Authorize by lazy {
            Retrofit.Builder()
                    .baseUrl("http://private-26abae-peterbukhal.apiary-mock.com/")
                    .client(OkHttpClient.Builder()
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                level = BODY
                            })
                            .build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(Authorize::class.java)
        }

    }

    @POST("authorize")
    fun authorize(@Body request: AuthorizeRequest): Observable<AuthorizeResponse>

    @POST("authorize/confirmation")
    fun authorizeConfirmation(@Body request: AuthorizeConfirmationRequest): Observable<AuthorizeConfirmationResponse>

}