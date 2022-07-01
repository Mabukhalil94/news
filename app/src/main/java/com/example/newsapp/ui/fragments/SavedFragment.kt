package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.ui.NewsViewModel

class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    lateinit var viewModel : NewsViewModel
    lateinit var newsAdapter : NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedFragment_to_articleFragment, bundle
            )
        }
    }
    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}