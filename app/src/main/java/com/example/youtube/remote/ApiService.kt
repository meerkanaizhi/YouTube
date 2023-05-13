package com.example.youtube.remote

import com.example.youtube.model.Playlists
import com.example.youtube.model.PlaylistItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query ("key") apiKey: String,
        @Query ("part") part: String,
        @Query("channelId") channelId: String,
    ) : Call<Playlists>
    @GET("playlistItems")
    fun getItemLists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("playlistId") id: String,
    ):Call<PlaylistItem>

}