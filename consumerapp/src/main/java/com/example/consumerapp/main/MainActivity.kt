package com.example.consumerapp.main

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerapp.R
import com.example.consumerapp.databinding.ActivityMainBinding
import com.example.consumerapp.util.toListUserFavorite
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(applicationContext)
    }

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fetchDataFromContentProvider()
    }

    private fun fetchDataFromContentProvider() {
        val table = "user_favorite_table"
        val authority = "com.example.githubusers.provider"

        val uri : Uri = Uri.Builder()
            .scheme("content")
            .authority(authority)
            .appendPath(table)
            .build()

        val contentResolver = this.contentResolver
        val cursor = contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            initAdapter(cursor)
        } else {
            initAdapter(cursor)
        }
    }

    private fun initAdapter(cursor: Cursor?) {
        binding.rcUser.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        cursor?.let {
            mainAdapter.setItems(it.toListUserFavorite())
        }

    }
}
