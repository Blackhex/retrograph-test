package com.kiwi.retrographtest.viewmodel

import android.util.*

import androidx.lifecycle.*

import com.kiwi.mobile.retrograph.*
import com.kiwi.retrographtest.model.*
import com.kiwi.retrographtest.service.*

import io.reactivex.*

import okhttp3.*
import okhttp3.logging.*

import retrofit2.*
import retrofit2.converter.gson.*

import java.util.concurrent.*

class MainViewModel:
  ViewModel() {

  private val tag = javaClass.name

  private val service = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(GraphQLCallAdapterFactory.create())
    .client(
      OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(
          HttpLoggingInterceptor()
            .apply {
              level = HttpLoggingInterceptor.Level.BASIC
            }
        )
        .build()
    )
    .build()
    .create<GitHubRequestService>()

  fun getRepositories(): Single<Query> {
    val request = RequestBuilder()
      .operation()
      .fieldsOf<Query>(
        QueryArguments(
          search = SearchArguments(
            query = "Test",
            type = Type.REPOSITORY,
            first = 10
          )
        )
      )
      .finish()
      .build()

    Log.d(tag, "getRepositories(): request: $request")

    return service.query(request)
  }
}
