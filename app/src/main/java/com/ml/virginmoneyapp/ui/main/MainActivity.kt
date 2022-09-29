package com.ml.virginmoneyapp.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import com.ml.virginmoneyapp.databinding.ActivityMainBinding
import com.ml.virginmoneyapp.ui.main.adapter.PostAdapter
import com.ml.virginmoneyapp.ui.viewmodel.VirginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val virginViewModel by viewModel<VirginViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupFlowCollector()
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter { navigateToPost(it.url) }
        binding.postsRecyclerview.adapter = postAdapter
    }

    private fun setupFlowCollector() {
        lifecycleScope.launch {
            virginViewModel.fetchPosts().collectLatest {
                postAdapter.submitData(it)
            }
        }
    }

    private fun navigateToPost(url: String) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));

    }
}