package local.zva.hw3.view.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import local.zva.hw3.utils.AnimationHelper
import local.zva.hw3.R
import local.zva.hw3.databinding.FragmentWatchLaterBinding

class WatchLaterFragment : Fragment() {
    private var _binding: FragmentWatchLaterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchLaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val revealEndListener = object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                requireActivity().findViewById<View>(R.id.main_activity).background = binding.root.background
            }
        }

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.watchFragmentRoot,
            requireActivity(),
            revealEndListener
        )
    }
}