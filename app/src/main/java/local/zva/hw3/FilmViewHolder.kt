package local.zva.hw3
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import local.zva.hw3.databinding.FilmItemBinding

class FilmViewHolder(filmItem: View) : RecyclerView.ViewHolder(filmItem) {
    private val binding = FilmItemBinding.bind(filmItem)
    fun bind(film: Film) = with(binding){
        title.text = film.title
        description.text = film.description
        Picasso.get()
            .load(film.poster)
            .resize(150, 150)
            .centerCrop()
            .into(binding.poster)
    }
}