package com.example.everydaynewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.everydaynewa.adapter.NewsAdapter
import com.example.everydaynewa.model.Article
import com.example.everydaynewa.utils.OnClickListener
import com.example.everydaynewa.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , OnClickListener{

    lateinit var viewModel:MainActivityViewModel
    lateinit var recyclerAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                val searchView = item.actionView as SearchView
                searchView.queryHint = resources.getString(R.string.search)
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.setFilter(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        viewModel.setFilter(newText)
                        return false
                    }

                })
            }
        }
        return true
    }

    private fun initRecyclerView() {
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerAdapter = NewsAdapter(this@MainActivity)
            adapter = recyclerAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.setFilter("")
        viewModel.article.observe(this,{
            recyclerAdapter.submitData(lifecycle,it)
        })
    }



    override fun onArticleCLicked(article: Article) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("title",article.title)
        intent.putExtra("image",article.urlToImage)
        intent.putExtra("description", article.description)
        intent.putExtra("content",article.content)
        startActivity(intent)

    }
}