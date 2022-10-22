package com.example.week5assignment.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.week5assignment.data.model.PostDTO
import com.example.week5assignment.databinding.FragmentFavoritesBinding
import com.example.week5assignment.ui.favorites.adapter.FavoritesAdapter
import com.example.week5assignment.ui.favorites.adapter.OnFavoriteClickListener
import com.example.week5assignment.ui.favorites.viewmodel.FavoritesViewModel
import com.example.week5assignment.ui.loadingprogress.LoadingProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), OnFavoriteClickListener {
    lateinit var loadingProgressBar: LoadingProgressBar
    private val viewModel by viewModels<FavoritesViewModel>()
    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBar = LoadingProgressBar(requireContext())

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner) {

            binding.rvFavoritesList.adapter = FavoritesAdapter(this).apply {
                submitList(it)
            }
        }



    }

    override fun onFavoriteClick(post: PostDTO) {
        viewModel.onFavoritePost(post)
        viewModel.getPosts()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPosts()
    }



}

