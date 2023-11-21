package local.zva.hw3
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import local.zva.hw3.databinding.FilmItemBinding
import kotlin.math.abs

class FilmViewHolder(filmItem: View) : RecyclerView.ViewHolder(filmItem) {
    private val binding = FilmItemBinding.bind(filmItem)
    fun bind(film: Film) = with(binding){
        title.text = film.title
        description.text = film.description
        Picasso.get()
            .load(film.poster)
            .resize(150, 150)
            .centerCrop()
            .into(binding.poster)
        ratingDonut.setRating((film.rating * 10).toInt())
        ratingDonut.digitAlpha = 0F
        animateProgress(0, ratingDonut.progress, ratingDonut)
    }

    private fun animateProgress(start: Int, end: Int, view: RatingDonutView) {
        val multiplier = abs(end - start) / 100f
        val duration = (2000 * multiplier).toLong()

        val animatorProgress = ValueAnimator.ofInt(start, end)
        animatorProgress.addUpdateListener {
            view.progress = it.animatedValue as Int
            view.invalidate()
        }
        animatorProgress.apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
        }

        val animatorDigit = ValueAnimator.ofFloat(0f, 1f)
        animatorDigit.addUpdateListener {
            view.digitAlpha = it.animatedValue as Float
            view.invalidate()
        }
        animatorDigit.apply {
            this.duration = 300
            interpolator = LinearInterpolator()
        }

        val animatorSet = AnimatorSet()
        animatorSet.apply {
            play(animatorProgress).before(animatorDigit)
            start()
        }
    }
}