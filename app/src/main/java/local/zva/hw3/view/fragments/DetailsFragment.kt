package local.zva.hw3.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import local.zva.hw3.R
import local.zva.hw3.data.ApiConstants
import local.zva.hw3.databinding.FragmentDetailsBinding
import local.zva.hw3.domain.Film


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
            } else {
                favBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
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
//        binding.detailsPoster.setImageResource(film.poster)
        Picasso.get()
            .load(ApiConstants.IMAGES_URL + "w780" + film.poster)
            .resize(780, 780)
            .centerCrop()
            .into(binding.detailsPoster)
    }

}