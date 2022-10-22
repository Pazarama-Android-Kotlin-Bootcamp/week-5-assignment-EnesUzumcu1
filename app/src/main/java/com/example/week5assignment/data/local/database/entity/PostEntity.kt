package com.example.week5assignment.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.week5assignment.utils.Constants

//database tablosu olusturuldu
@Entity(tableName = Constants.TABLE_POST_NAME)
class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "postId") val postId: String?,
    @ColumnInfo(name = "postTitle") val postTitle: String?,
    @ColumnInfo(name = "postBody") val postBody: String?,
)