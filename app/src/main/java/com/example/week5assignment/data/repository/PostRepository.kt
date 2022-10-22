package com.example.week5assignment.data.repository

import retrofit2.Call
import com.example.week5assignment.data.local.database.entity.PostEntity
import com.example.week5assignment.data.model.Post

interface PostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: String) : Call<Post>
    fun getPostById(id: Int): PostEntity?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(id: String)
    fun getFavorites(): List<PostEntity>
}