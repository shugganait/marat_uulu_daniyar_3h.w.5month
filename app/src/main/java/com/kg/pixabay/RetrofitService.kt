package com.kg.pixabay

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private var retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    var api = retrofit.create(PixaBayApi::class.java)
}
/*<androidx.recyclerview.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="fill_parent"
        android:scrollbars="vertical"
        app:layoutManager="androidx.recyclerview.widget.StaggeredGridLayoutManager" />*/