package local.zva.hw3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import local.zva.hw3.domain.Film
import local.zva.hw3.domain.Interactor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeFragmentVM : ViewModel(), KoinComponent {

    val filmsListLiveData = MutableLiveData<List<Film>>()
    private val interactor: Interactor by inject()
    private var page = 1

    init {
        interactor.getFilmsFromApi(page, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }

            override fun onFailure() { }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}