package com.kiwi.retrographtest.model

import com.kiwi.mobile.retrograph.annotation.*

data class Query(
  val search: SearchResultItemConnection
)

data class SearchResultItemConnection(
  @field:InlineFragment
  val nodes: List<Repository>
)

data class Repository(
  val id: String,

  @field:Alias("name")
  val repositoryName: String
)

data class QueryArguments(
  val search: SearchArguments
)

data class SearchArguments(
  val query: String,
  val type: Type,
  val first: Int
)

enum class Type {
  REPOSITORY
}
