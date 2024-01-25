package local.zva.hw3

import android.app.Application
import local.zva.hw3.DI.DI
import local.zva.hw3.data.MainRepository
import local.zva.hw3.data.TmdbApi
import local.zva.hw3.domain.Interactor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    private lateinit var repository: MainRepository
    lateinit var interactor: Interactor
    lateinit var retrofitService: TmdbApi

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(DI.mainModule))
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}