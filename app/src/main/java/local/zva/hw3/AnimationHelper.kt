package local.zva.hw3

import android.app.Activity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import java.util.concurrent.Executors
import kotlin.math.hypot


object AnimationHelper {
    private const val menuItems = 4

    fun performFragmentCircularRevealAnimation(rootView: View, activity: Activity, position: Int) {
        Executors.newSingleThreadExecutor().execute {
            while (true) {
                if (rootView.isAttachedToWindow) {
                    activity.runOnUiThread {
                        /*val itemCenter = rootView.width / (menuItems * 2)
                        val step = (itemCenter * 2) * (position - 1) + itemCenter

                        val x: Int = step
                        val y: Int = rootView.y.roundToInt() + rootView.height*/

                        val x: Int = (0..rootView.width).random()
                        val y: Int = (0..rootView.height).random()



                        val startRadius = 0
                        val endRadius = hypot(rootView.width.toDouble(), rootView.height.toDouble())

                        fun getX(): Int {
                            return (0..rootView.width).random()
                        }
                        fun getY(): Int {
                            return (0..rootView.width).random()
                        }

                        ViewAnimationUtils.createCircularReveal(rootView, getX(), getY(),
                            startRadius.toFloat(), endRadius.toFloat()).apply {
                            duration = 500
                            interpolator = AccelerateDecelerateInterpolator()
                            start()
                        }

                        rootView.visibility = View.VISIBLE
                    }
                    return@execute
                }
            }
        }
    }
}