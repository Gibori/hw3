package local.zva.hw3

import android.app.Application
import com.squareup.picasso.BuildConfig
import local.zva.hw3.data.ApiConstants
import local.zva.hw3.data.MainRepository
import local.zva.hw3.data.TmdbApi
import local.zva.hw3.domain.Interactor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {
    private lateinit var repository: MainRepository
    lateinit var interactor: Interactor
    lateinit var retrofitService: TmdbApi

    override fun onCreate() {
        super.onCreate()

        instance = this
        repository = MainRepository()
//        interactor = Interactor(repository)

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofitService = retrofit.create(TmdbApi::class.java)
        interactor = Interactor(repository, retrofitService)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}