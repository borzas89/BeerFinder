package example.com.beerfinder.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Provides
import example.com.beerfinder.data.api.MarkerApiModel
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*

interface MarkersService {

      @GET("/assets/json/beer_finder_demo.json")
      fun getMarkers(): Deferred<List<MarkerApiModel>>

      companion object {
            private const val BASE_URL = "http://totalstats.infora.hu/"


            fun create(): MarkersService {
                  val moshi = Moshi.Builder()
                        .add(Date::class.java, Rfc3339DateJsonAdapter())
                        .build()

                  return Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .addCallAdapterFactory(CoroutineCallAdapterFactory())
                        .build()
                        .create(MarkersService::class.java)
            }
      }


}