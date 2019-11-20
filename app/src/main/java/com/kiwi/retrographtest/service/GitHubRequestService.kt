package com.kiwi.retrographtest.service

import com.kiwi.mobile.retrograph.annotation.*
import com.kiwi.mobile.retrograph.model.*

import com.kiwi.retrographtest.model.Query

import io.reactivex.*

import retrofit2.http.*

interface GitHubRequestService {
  @POST("graphql")
  @GraphQL
  @Headers("Authorization: bearer ef75d8ee8746df15d979b56cf08686d70a26f7d3")
  fun query(@Body request: Request): Single<Query>
}
