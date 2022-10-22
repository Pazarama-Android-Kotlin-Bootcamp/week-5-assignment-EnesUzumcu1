package com.example.week5assignment.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.week5assignment.R
import com.example.week5assignment.data.model.DataState
import com.example.week5assignment.data.model.PostDTO
import com.example.week5assignment.databinding.FragmentPostsBinding
import com.example.week5assignment.ui.loadingprogress.LoadingProgressBar
import com.example.week5assignment.ui.posts.adapter.OnPostClickListener
import com.example.week5assignment.ui.posts.adapter.PostsAdapter
import com.example.week5assignment.ui.posts.viewmodel.PostViewEvent
import com.example.week5assignment.ui.posts.viewmodel.PostsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), OnPostClickListener {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentPostsBinding
    private val viewModel by viewModels<PostsViewModel>()

    private lateinit var navController: NavController

    companion object{
        var postIdGlobal : String =""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        loadingProgressBar = LoadingProgressBar(requireContext())

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner){
            when(it){
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData->
                        binding.rvPostsList.adapter = PostsAdapter(this).apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(),"No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root,it.message,Snackbar.LENGTH_LONG).show()

                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }

        viewModel.eventStateLiveData.observe(viewLifecycleOwner){
            when (it) {
                is PostViewEvent.ShowMessage -> {}
                is PostViewEvent.NavigateToDetail -> {}
            }
        }
    }

    override fun onPostClick(post: PostDTO) {
        viewModel.onFavoritePost(post)
    }

    override fun onClickListener(postId: String) {
        navController.navigate(R.id.action_postsFragment_to_postDetailFragment, Bundle().apply {
            putString("lat", postId)
            postIdGlobal = postId
        })
    }


}