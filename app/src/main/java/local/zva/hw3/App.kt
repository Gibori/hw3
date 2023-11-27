package local.zva.hw3

import android.app.Application
import local.zva.hw3.data.MainRepository
import local.zva.hw3.domain.Interactor

class App : Application() {
    private lateinit var repository: MainRepository
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()

        instance = this
        repository = MainRepository()
        interactor = Interactor(repository)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}