package local.zva.hw3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import local.zva.hw3.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var film: Film

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmDetails()

        val favBtn = binding.detailsFabFavorites
        favBtn.setImageResource(
            if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
        favBtn.setOnClickListener {
            if (film.isInFavorites) {
                favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            } else {
                favBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            }
        }

        binding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share to:"))
        }
    }

    private fun setFilmDetails() {
        film = arguments?.get("film") as Film

        binding.detailsToolbar.title = film.title
        binding.detailsDescription.text = film.description
        binding.detailsPoster.setImageResource(film.poster)
    }

}