package local.zva.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import local.zva.hw3.databinding.FragmentWatchLaterBinding

class WatchLaterFragment : Fragment() {
    private var _binding: FragmentWatchLaterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchLaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currCount = requireActivity().supportFragmentManager.backStackEntryCount - 1
        val prevCount = requireActivity().supportFragmentManager.backStackEntryCount - 2
        val currTag = requireActivity().supportFragmentManager.getBackStackEntryAt(currCount)
            .name
        val prevTag = requireActivity().supportFragmentManager.getBackStackEntryAt(prevCount)
            .name

        binding.txtCentr.text = "$prevCount - $prevTag\n$currCount - $currTag"

        AnimationHelper.performFragmentCircularRevealAnimation(binding.watchFragmentRoot, requireActivity(), 3)
    }
}