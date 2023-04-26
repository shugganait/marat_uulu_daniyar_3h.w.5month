package com.kg.pixabay

import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kg.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var page = 1
    private var count = 0
    val adapter = PixaAdapter(arrayListOf())
    var globalList = arrayListOf<ImageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.pbLoading.visibility = GONE
        setContentView(binding.root)
        initListeners()
        setUpRV()
    }

    private fun setUpRV() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun initListeners() {
        binding.apply {
            btnSearch.setOnClickListener {
                pbLoading.visibility = VISIBLE
                page = 1
                adapter.list.clear()
                requestImages(page)
            }
            btnNextPage.setOnClickListener {
                page++
                requestImages(page)
            }
            nestedScrollView.setOnScrollChangeListener(OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.measuredHeight - v.measuredHeight) {
                    count++
                    pbLoading.visibility = VISIBLE
                    if (count < 20) {
                        page++
                        requestImages(page)
                    }
                }
            })
        }
    }

    private fun ActivityMainBinding.requestImages(page: Int) {
        RetrofitService().api.getImages(etSearchWord.text.toString(), page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    response.body()?.let {
                        globalList = it.hits
                        adapter.addImages(globalList)
                        binding.recyclerView.adapter = adapter
                    }
                }
                override fun onFailure(call: Call<PixaModel>, t: Throwable) {

                }
            })
    }
}