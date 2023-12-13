package local.zva.hw3.utils

import local.zva.hw3.data.entity.TmdbFilm
import local.zva.hw3.domain.Film

object Converter {
    fun convertApiListToDtoList(listApi: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        listApi?.forEach {
            result.add(Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
            ))
        }
        return result
    }
}