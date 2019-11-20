package com.kiwi.retrographtest.activity

import android.os.*
import android.util.*

import androidx.appcompat.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.LinearLayoutManager

import com.kiwi.retrographtest.R
import com.kiwi.retrographtest.adapter.*
import com.kiwi.retrographtest.viewmodel.*

import io.reactivex.android.schedulers.*
import io.reactivex.disposables.*
import io.reactivex.schedulers.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity:
  AppCompatActivity() {

  private val tag = javaClass.name

  private lateinit var viewModel: MainViewModel
  private val adapter = RepositoryAdapter()
  private val subscriptions = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    recycler_view.layoutManager = LinearLayoutManager(this)
    recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    recycler_view.adapter = adapter

    fab.setOnClickListener { reload() }
  }

  override fun onResume() {
    super.onResume()

    reload()
  }

  override fun onDestroy() {
    super.onDestroy()

    subscriptions.dispose()
  }

  private fun reload() {
    Log.d(tag, "reload()")

    subscriptions.add(
      viewModel.getRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
          {
            Log.d(tag, "onGetRepositoriesSuccess(): response $it")

            adapter.items = it.search.nodes
          },
          { Log.e(tag, "onGetRepositoriesError()", it) }
        )
    )
  }
}
