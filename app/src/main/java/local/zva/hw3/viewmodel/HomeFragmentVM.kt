package local.zva.hw3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import local.zva.hw3.App
import local.zva.hw3.domain.Film

class HomeFragmentVM : ViewModel() {

    val filmsListLiveData = MutableLiveData<List<Film>>()
    private var interactor = App.instance.interactor

    init {
        interactor.getFilmsFromApi(1, object : ApiCallback {
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