package local.zva.hw3
import androidx.recyclerview.widget.RecyclerView
import local.zva.hw3.databinding.FilmItemBinding

class FilmViewHolder(filmViewBinding: FilmItemBinding) : RecyclerView.ViewHolder(filmViewBinding.root) {
    private val title = filmViewBinding.title
    private val description = filmViewBinding.description
    private val poster = filmViewBinding.poster

    fun bind(film: Film){
        title.text = film.title
        description.text = film.description
        poster.setImageResource(film.poster)
    }
}