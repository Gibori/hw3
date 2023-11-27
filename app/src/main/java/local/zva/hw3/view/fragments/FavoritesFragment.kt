package local.zva.hw3.view.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import local.zva.hw3.utils.AnimationHelper
import local.zva.hw3.view.rv_adapters.FilmListRecyclerAdapter
import local.zva.hw3.view.MainActivity
import local.zva.hw3.R
import local.zva.hw3.view.rv_adapters.TopSpacingItemDecoration
import local.zva.hw3.databinding.FragmentFavoritesBinding
import local.zva.hw3.domain.Film

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesList: List<Film> = emptyList()

        binding.favoritesRecycler
            .apply {
                filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.onItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailFragment(film)
                    }
                })

                adapter = filmsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        filmsAdapter.addItems(favoritesList)

        val revealEndListener = object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                requireActivity().findViewById<View>(R.id.main_activity).background = binding.root.background
            }
        }

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.favoritesFragmentRoot,
            requireActivity(),
            revealEndListener
        )
    }
}