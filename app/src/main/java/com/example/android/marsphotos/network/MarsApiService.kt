package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// 新增 web service 常數 base URL
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

// 使用 Moshi builder 建立 Moshi object: moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/* Retrofit 需要 web service 的 base URI 和 converter factory，以 build web services API，
   converter 會向 Retrofit 告知如何處理從 web service 傳回的 data
*/
// 使用 Retrofit builder 建立 Retrofit object: retrofit，並傳遞 moshi instance
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/* 新增 MarsApiService interface，定義 Retrofit 使用 HTTP requests 與 web server 溝通的方式 */
interface MarsApiService {

    // 使用 GET request 從 web service 取得 response(endpoint為/photos)，
    // 將 return type 設為 MarsPhoto objects list
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>

}

/* 定義名為 MarsApi 的 public object，以初始化 Retrofit service
   (這是可從 app 其餘部分存取的 public singleton object) */
object MarsApi {
    /* 新增名為 retrofitService 的延遲(lazy)初始化 Retrofit object 屬性 (type 為 MarsApiService)
       (執行此延遲初始化的用意，在於確保其在第一次使用時已初始化) */
    val retrofitService: MarsApiService by lazy {
        // 透過 MarsApiService interface，使用 retrofit.create() 方法初始化 retrofitService
        retrofit.create(MarsApiService::class.java)
    }
}