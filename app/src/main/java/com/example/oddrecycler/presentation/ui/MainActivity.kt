package com.example.oddrecycler.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oddrecycler.data.entity.Element
import com.example.oddrecycler.presentation.viewmodel.ElementsViewModel
import com.example.oddrecycler.R
import com.example.oddrecycler.presentation.adapter.RecyclerAdapter
import com.example.oddrecycler.presentation.util.RecyclerAnimator
import com.example.oddrecycler.presentation.util.RecyclerItemDecoration
import com.example.oddrecycler.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var recyclerAdapter: RecyclerAdapter? = null
    private val viewModel: ElementsViewModel by viewModels()
    @Inject lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        findViewById<RecyclerView>(R.id.recycler)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buildRecycler()
        startDataObserver()
        setupRefresh()
    }

    private fun buildRecycler() {
        recyclerAdapter = RecyclerAdapter() { id ->
            viewModel.removeElement(id)
        }.apply {
            data = viewModel.elements.replayCache.let {
                if (it.isEmpty())
                    listOf()
                else
                    it.last()
            }
        }
        val recyclerLayoutManager = GridLayoutManager(this@MainActivity, 3)
        binding?.recycler?.run {
            adapter = recyclerAdapter
            layoutManager = recyclerLayoutManager
            addItemDecoration(RecyclerItemDecoration(bottomOffset = 16, rightOffset = 8, leftOffset = 8))
            addItemDecoration(DividerItemDecoration(this@MainActivity, recyclerLayoutManager.orientation))
            itemAnimator = RecyclerAnimator()
        }
    }

    private fun startDataObserver() {
        scope.launch(Dispatchers.Default) {
            viewModel.elements.collect {
                withContext(Dispatchers.Main) {
                    updateRecyclerData(it)
                }
            }
        }
    }

    private fun setupRefresh() {
        binding?.refresh?.run {
            setOnRefreshListener {
         //       updateRecyclerData(viewModel.elements.)
                isRefreshing = false
            }
        }
    }

    private fun updateRecyclerData(elements: List<Element>) {
        Log.d("ACTIVITY", "new data: $elements")
        recyclerAdapter?.apply {
            data = elements
        }
    }
}