package com.yusril.doaharian.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusril.doaharian.R
import com.yusril.doaharian.core.data.Resource
import com.yusril.doaharian.core.ui.DoaAdapter
import com.yusril.doaharian.core.ui.ViewModelFactory
import com.yusril.doaharian.databinding.ActivityFavoriteBinding
import com.yusril.doaharian.ui.detail.DetailActivity
import com.yusril.doaharian.ui.home.HomeViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.favorite)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        val doaAdapter = DoaAdapter()
        doaAdapter.onItemClick = { selectedDoa ->
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra(DetailActivity.DETAIL_DOA, selectedDoa)
            startActivity(i)
        }

        viewModel.favoriteDoa.observe(this, { doa ->
            doaAdapter.setData(doa)
            binding.viewEmpty.root.visibility = if (doa.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvDoa) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = doaAdapter
        }
    }
}