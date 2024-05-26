package com.example.oddrecycler.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oddrecycler.presentation.viewmodel.ElementsViewModel
import com.example.oddrecycler.R
import com.example.oddrecycler.presentation.util.RecyclerAnimator
import com.example.oddrecycler.presentation.util.RecyclerItemDecoration
import com.example.oddrecycler.databinding.ActivityMainBinding
import com.example.oddrecycler.util.Dispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel: ElementsViewModel by viewModels()
    @Inject lateinit var scope: CoroutineScope
    @Inject lateinit var dispatcher: Dispatcher
    @Inject lateinit var recyclerLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setupView()
        buildRecycler()
        viewModel.startDataObserver()
    }

    private fun setupView() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun buildRecycler() {
        binding?.recycler?.apply {
            adapter = viewModel.adapter
            layoutManager = recyclerLayoutManager
            addItemDecoration(
                RecyclerItemDecoration(
                    bottomOffset = 16,
                    rightOffset = 8,
                    leftOffset = 8
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    recyclerLayoutManager.orientation
                )
            )
            itemAnimator = RecyclerAnimator()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}