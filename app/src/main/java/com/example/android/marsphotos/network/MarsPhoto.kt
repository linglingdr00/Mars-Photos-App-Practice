package com.example.android.marsphotos.network

import com.squareup.moshi.Json

/* 將轉換完成的 Kotlin objects 儲存在 data class */
/* 每個變數皆會對應至 JSON object 中的 key name，如要在 data class 中使用
   與 JSON response 中 key name 不同的變數名稱，可使用 @Json 註解 */
data class MarsPhoto(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String? = null
)