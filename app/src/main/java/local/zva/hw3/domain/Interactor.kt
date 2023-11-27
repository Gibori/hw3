package local.zva.hw3.domain

import local.zva.hw3.data.MainRepository

class Interactor(val repository: MainRepository) {
    fun getDB(): List<Film> = repository.filmDataBase
}