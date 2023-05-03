package com.example.youtube.ui.playlists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.R
import com.example.youtube.base.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistsBinding


class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this).get(PlaylistsViewModel::class.java)
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.playlists().observe(this){
            Toast.makeText(this, it.kind.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
    return ActivityPlaylistsBinding.inflate(layoutInflater)

    }
}