package com.kiwi.retrographtest.adapter

import android.view.*

import androidx.recyclerview.widget.RecyclerView.*

import com.kiwi.retrographtest.*
import com.kiwi.retrographtest.model.*

import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter:
  Adapter<ViewHolder>() {

  class RepositoryViewHolder(parent: ViewGroup):
    ViewHolder(createLayout(parent)) {

    companion object {
      private fun createLayout(parent: ViewGroup) =
        LayoutInflater.from(parent.context)
          .inflate(R.layout.item_repository, parent, false)
    }

    fun bind(repository: Repository) {
      itemView.name.text = repository.repositoryName
    }
  }

  var items = listOf<Repository>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun getItemCount() =
    items.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    RepositoryViewHolder(parent)

  override fun onBindViewHolder(holder: ViewHolder, position: Int) =
    (holder as RepositoryViewHolder).bind(items[position])
}
