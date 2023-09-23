package local.zva.hw3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import local.zva.hw3.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingDA = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(bindingDA.root)

        val film = intent.extras?.get("film") as Film

        bindingDA.detailsToolbar.title = film.title
        bindingDA.detailsDescription.text = film.description
        bindingDA.detailsPoster.setImageResource(film.poster)
    }
}