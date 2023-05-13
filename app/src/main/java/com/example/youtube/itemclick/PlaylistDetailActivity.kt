package com.example.youtube.itemclick

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.adapter.ItemAdapter
import com.example.youtube.base.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistDetailBinding

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding, DetailViewModel>() {
    private lateinit var adapter: ItemAdapter
    private val id: String?
        get() = intent.getStringExtra("id")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemAdapter()
        setselectedItems()
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)

    }

    override val viewModel: DetailViewModel
        get() = ViewModelProvider(this)[DetailViewModel::class.java]

    private fun setselectedItems() {
        id.let {
            if (it != null) {
                viewModel.itemList(id = it).observe(this) {
                    binding.recVSelected.adapter = adapter
                    adapter.setselectedList(it.items)
                }
            }
        }
    }
}
