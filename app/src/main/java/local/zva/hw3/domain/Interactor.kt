package local.zva.hw3.domain

import local.zva.hw3.data.ApiKey
import local.zva.hw3.data.MainRepository
import local.zva.hw3.data.TmdbApi
import local.zva.hw3.data.entity.TmdbResultsDto
import local.zva.hw3.utils.Converter
import local.zva.hw3.viewmodel.HomeFragmentVM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repository: MainRepository, private val retrofitService: TmdbApi) {



    fun getFilmsFromApi(page: Int, callback: HomeFragmentVM.ApiCallback) {
        retrofitService.getFilms(ApiKey.KEY, "ru-RU", page)
            .enqueue(object : Callback<TmdbResultsDto> {
                override fun onResponse(
                    call: Call<TmdbResultsDto>,
                    response: Response<TmdbResultsDto>
                ) {
                    callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
                }

                override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                    println("!!! при загрузке что-то пошло не так")
                    println("!!! $")
                    callback.onFailure()
                }
            })
    }
}