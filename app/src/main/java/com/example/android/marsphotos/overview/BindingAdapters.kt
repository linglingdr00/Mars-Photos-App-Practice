package com.example.android.marsphotos.overview

import android.provider.ContactsContract.Contacts.Photo
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.R
import com.example.android.marsphotos.network.MarsPhoto

/* @BindingAdapter 註解會通知 data binding，
   在 View item 擁有 ImageView 的 imageUrl 屬性時執行此 binding adapter */

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // 使用 toUri() 將 URL string 轉換為 Uri object (將 buildUpon.scheme("https") 附加至 toUri builder)
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        // 使用 Coil 的 load(){} 將 imgUri object 的 image 載入 imgView
        imgView.load(imgUri) {
            // 使用 Coil 的 placeholder() 和 error() 設定 image 載入時和載入失敗時要使用的 drawable
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/* 初始化包含 MarsPhoto objects list 的 PhotoGridAdapter */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    // 將 recyclerView.adapter 做為 PhotoGridAdapter 並指派給新的 val 屬性 adapter
    val adapter = recyclerView.adapter as PhotoGridAdapter
    // 呼叫 adapter.submitList() 可查看 Mars photos list data (出現新的 list 時，這個屬性會通知 RecyclerView)
    adapter.submitList(data)

}

/* 設定 status 以顯示 loading 和 error status 的 icon */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            // 將 ImageView 設為 visible
            statusImageView.visibility = View.VISIBLE
            // 設定 loading animation
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            // 將 state ImageView 設為 visible
            statusImageView.visibility = View.VISIBLE
            // 設定 connection-error drawable
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            // 將 state ImageView 的 visibility 設定為 GONE
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}

class BindingAdapters {
}