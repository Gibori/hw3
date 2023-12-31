package local.zva.hw3.view.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import local.zva.hw3.R
import local.zva.hw3.databinding.FragmentHomeBinding
import local.zva.hw3.domain.Film
import local.zva.hw3.utils.AnimationHelper
import local.zva.hw3.view.MainActivity
import local.zva.hw3.view.customviews.RatingDonutView
import local.zva.hw3.view.rv_adapters.FilmListRecyclerAdapter
import local.zva.hw3.view.rv_adapters.TopSpacingItemDecoration
import local.zva.hw3.viewmodel.HomeFragmentVM
import java.util.Locale

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentVM::class.java)
    }
    private var filmDataBase = listOf<Film>()
        set(value) {
            if (field == value) return
            field = value
            filmsAdapter.addItems(field)
        }
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        retainInstance = true
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer<List<Film>> {
            filmDataBase = it
        })

        binding.homeFragmentRoot.findViewById<SearchView>(R.id.search_view)
            .setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmDataBase)
                    return true
                }
                val result = filmDataBase.filter {
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }
                filmsAdapter.addItems(result)
                return true
            }
        })
        initRV()
        filmsAdapter.addItems(filmDataBase)

        val revealEndListener = object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                requireActivity().findViewById<View>(R.id.main_activity).background = binding.root.background
            }
        }

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            revealEndListener
        )
    }

    private fun initRV() {
        binding.homeFragmentRoot.findViewById<RecyclerView>(R.id.recycler_main).apply {
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
    }


    private fun animateRatingDonutView(view: RatingDonutView, start: Int, end: Int) {
        //аниматор для бублика
        val ratingAnimator = ValueAnimator.ofInt()
        ratingAnimator.apply {
            addUpdateListener {
                view.progress = it.animatedValue as Int
                view.invalidate()
            }
            duration = (1000 * (end - start) / 100f).toLong()
            interpolator = AccelerateDecelerateInterpolator()
        }

        val animatorSet = AnimatorSet()
        animatorSet.apply {
            play(ratingAnimator)
            start()
        }
    }
}

