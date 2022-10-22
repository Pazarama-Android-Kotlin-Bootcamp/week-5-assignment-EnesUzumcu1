package com.example.week5assignment.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week5assignment.data.local.database.entity.PostEntity
import com.example.week5assignment.data.model.Post
import com.example.week5assignment.data.repository.PostRepository
import com.example.week5assignment.ui.posts.PostsFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(private val postRepository: PostRepository) :
    ViewModel() {
    private var id: String = PostsFragment.postIdGlobal

    private var _postLiveData = MutableLiveData<Post>()
    val postLiveData: LiveData<Post>
        get() = _postLiveData

    private val _eventStateLiveData = MutableLiveData<PostViewEvent>()
    val eventStateLiveData: LiveData<PostViewEvent>
        get() = _eventStateLiveData

    init {
        getPosts()
    }

    private fun getPosts() {
        postRepository.getPostById(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _postLiveData.postValue(it)
                    } ?: kotlin.run {

                    }
                } else {

                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

                _eventStateLiveData.postValue(PostViewEvent.ShowMessage(t.message.toString()))
            }
        })
    }
    private fun updateFavoriteState(post: Post, isFavorite:Boolean){
        when(val current= _postLiveData.value){



        }
    }

    fun onFavoritePost(post: Post) {
        postRepository.getPostById(post.id ?: 0)?.let {
            postRepository.deleteFavoritePost(
                post.id.toString()
            )
        } ?: kotlin.run {
            postRepository.insertFavoritePost(
                PostEntity(
                    postId = post.id.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
        }
    }
    fun isExists(postId: Int?): Boolean {
        postId?.let {
            postRepository.getPostById(it)?.let {
                return true
            }
        }
        return false
    }

}

sealed class PostViewEvent {
    object NavigateToDetail : PostViewEvent()
    class ShowMessage(val message: String?) : PostViewEvent()
}