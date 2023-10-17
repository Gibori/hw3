package local.zva.hw3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class FilmListRecyclerAdapter(private val clickListener: onItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Film>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(view)
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