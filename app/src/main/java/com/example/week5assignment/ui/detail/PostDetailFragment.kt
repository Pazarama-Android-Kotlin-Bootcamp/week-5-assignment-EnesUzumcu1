package com.example.week5assignment.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.week5assignment.R
import com.example.week5assignment.databinding.FragmentPostDetailBinding
import com.example.week5assignment.ui.detail.viewmodel.PostDetailViewModel
import com.example.week5assignment.ui.loadingprogress.LoadingProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentPostDetailBinding
    private val viewModel by viewModels<PostDetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBar = LoadingProgressBar(requireContext())

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner) {safePost->
            binding.tvTitleValue.text = safePost.title
            binding.tvBodyValue.text = safePost.body
            binding.ivPostImage.setOnClickListener{
                viewModel.onFavoritePost(safePost)

                if(viewModel.isExists(safePost.id)){
                    binding.ivPostImage.setImageResource(R.drawable.ic_red_favorite)
                }
                else{
                    binding.ivPostImage.setImageResource(R.drawable.ic_baseline_favorite_24)
                }

            }

        }
        viewModel.eventStateLiveData.observe(viewLifecycleOwner)
        {

        }
    }
}