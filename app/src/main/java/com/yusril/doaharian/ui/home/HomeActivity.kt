package com.yusril.doaharian.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusril.doaharian.R
import com.yusril.doaharian.core.data.Resource
import com.yusril.doaharian.core.ui.DoaAdapter
import com.yusril.doaharian.core.ui.ViewModelFactory
import com.yusril.doaharian.databinding.ActivityHomeBinding
import com.yusril.doaharian.ui.detail.DetailActivity
import com.yusril.doaharian.ui.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        val doaAdapter = DoaAdapter()
        doaAdapter.onItemClick = { selectedDoa ->
            val i = Intent(this, DetailActivity::class.java)
                i.putExtra(DetailActivity.DETAIL_DOA, selectedDoa)
            startActivity(i)
        }

        viewModel.doa.observe(this, { doa ->
            if (doa != null) {
                when (doa) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        doaAdapter.setData(doa.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = doa.message ?: getString(R.string.something_wrong)
                    }
                }
            }
            Log.d("DOA", doa.data.toString())
        })

        with(binding.rvDoa) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = doaAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.favorite_menu -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}