package com.example.youtube.ui.playlists

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.R
import com.example.youtube.adapter.PlaylistAdapter
import com.example.youtube.base.BaseActivity
import com.example.youtube.databinding.PlaylistsMainBinding
import com.example.youtube.internet.Connection
import com.example.youtube.itemclick.PlaylistDetailActivity
import com.example.youtube.model.Item


class PlaylistsActivity : BaseActivity<PlaylistsMainBinding,
        PlaylistsViewModel>() {

    private lateinit var adapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PlaylistAdapter(onItemClick = this::onItemClick)
    }

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this).get(PlaylistsViewModel::class.java)
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.playlists().observe(this){binding.recyclerView.layoutManager = LinearLayoutManager(this)

            binding.recyclerView.adapter = adapter
            adapter.setItem(it.items as ArrayList<Item>)
        }



         binding.connectionView.tryAgain.setOnClickListener {
        checkConnection()
         }

        // }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): PlaylistsMainBinding {
    return PlaylistsMainBinding.inflate(layoutInflater)

    }



        private fun onItemClick(list: Item){
        Intent(this@PlaylistsActivity, PlaylistDetailActivity::class.java).apply {
            putExtra("id", list.id)
            startActivity(this)
        }

    }

    override fun isConnection() {
        checkConnection()

    }
    private fun checkConnection() {
        val isConnection = Connection.isNetworkAvailable(this)
        if (!isConnection){
            Toast.makeText(this, getString(R.string.not_connection_message), Toast.LENGTH_SHORT).show()
        }
        binding.connectionContainer.isVisible = !isConnection

    }

}

