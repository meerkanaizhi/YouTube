package com.example.youtube.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig
import com.example.youtube.base.BaseViewModel
import com.example.youtube.model.Playlists
import com.example.youtube.remote.ApiService
import com.example.youtube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaylistsViewModel: BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<Playlists> {
        return getPlaylist();
    }

    private fun getPlaylist(): LiveData<Playlists> {

        val data = MutableLiveData<Playlists>()

        apiService.getPlaylists(BuildConfig.API_KEY, "contentDetails", "UCWOA1ZGywLbqmigxE4Qlvuw")
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful){
                        response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                print(t.stackTrace)

                    //ошибки: 404 - ot found , 403 - нет доступа, 401 -токен истек
                }
            })
        return  data
    }
}



