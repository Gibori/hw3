package local.zva.hw3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import local.zva.hw3.databinding.FilmItemBinding


class FilmListRecyclerAdapter(private val clickListener: onItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Film>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val filmViewBinding = FilmItemBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(filmViewBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(items[position])

                holder.itemView.findViewById<CardView>(R.id.item_container).setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    fun addItems(list: List<Film>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    interface onItemClickListener {
        fun click(film: Film)
    }
}