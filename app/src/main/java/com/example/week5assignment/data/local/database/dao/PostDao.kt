package com.example.week5assignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.week5assignment.data.local.database.base.BaseDao
import com.example.week5assignment.data.local.database.entity.PostEntity
import com.example.week5assignment.utils.Constants

@Dao
interface PostDao  : BaseDao<PostEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME}")
    fun getAllPosts(): List<PostEntity>

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME}")
    fun deleteAll()

    //veritabanından verilen idli satırı siler
    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun getPostById(postId: String): PostEntity?

    //veritabanından favoriyi siler
    @Query("DELETE FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun deleteFavoriteById(postId: String)
}