package local.zva.hw3

import android.os.Bundle
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import local.zva.hw3.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scene = Scene.getSceneForLayout(binding.homeFragmentRoot, R.layout.merge_home_screen_content, requireContext())
        TransitionManager.go(scene, initTransaction())


        binding.homeFragmentRoot.findViewById<SearchView>(R.id.search_view).setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    filmsAdapter.addItems((activity as MainActivity).filmDataBase)
                    return true
                }
                val result = MainActivity().filmDataBase.filter {
                    it.title.lowercase(Locale.getDefault()).contains(newText.lowercase(Locale.getDefault()))
                }
                filmsAdapter.addItems(result)
                return true
            }
        })
        initRV()
        filmsAdapter.addItems((activity as MainActivity).filmDataBase)
    }

    private fun initTransaction(): TransitionSet {
        val searchSlide = Slide(Gravity.TOP).addTarget(R.id.search_view)
        val rvSlide = Slide(Gravity.BOTTOM).addTarget(R.id.recycler_main)
        val transition = TransitionSet().apply {
            duration = 500
            addTransition(searchSlide)
            addTransition(rvSlide)
        }

        return transition
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
}

