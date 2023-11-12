package local.zva.hw3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

class RatingDonutView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) :
    View(context, attributeSet){
    private val oval = RectF()

    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var radius: Float = 0f

    private var stroke = 10f

    private var progress = 50

    private var scaleSize = 60f

    private lateinit var strokePaint: Paint
    private lateinit var digitPaint: Paint
    private lateinit var circlePaint: Paint

    init {
        val attributes = context.theme.obtainStyledAttributes(
            attributeSet, R.styleable.RatingDonutView, 0, 0)
        try {
            stroke = attributes.getFloat(R.styleable.RatingDonutView_stroke, stroke)
            progress = attributes.getInt(R.styleable.RatingDonutView_progress, progress)
        } finally {
            attributes.recycle()
        }
        initPaints()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = if (width < height) width / 2f else height / 2f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = chooseDimension(
            MeasureSpec.getMode(widthMeasureSpec),
            MeasureSpec.getSize(widthMeasureSpec)
        )
        val height = chooseDimension(
            MeasureSpec.getMode(heightMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec)
        )

        val minSide = width.coerceAtMost(height)

        centerX = minSide /2f
        centerY = minSide /2f

        setMeasuredDimension(minSide, minSide)
    }



    private fun drawRating(canvas: Canvas) {
        //размер кольца
        val scale = radius * 0.8f

        canvas.save()

        canvas.translate(centerX, centerY)
        oval.set(0f - scale, 0f - scale, scale, scale)
        canvas.drawCircle(0f, 0f, radius, circlePaint)
        canvas.drawArc(oval, -90f, convertProgressToDegrees(progress), false, strokePaint)

        canvas.restore()
    }

    private fun convertProgressToDegrees(progress: Int): Float {
        return progress * 3.6f
    }

    private fun chooseDimension(mode: Int, size: Int) = when (mode) {
        MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
        else -> 300
    }

    private fun initPaints() {
        strokePaint = Paint().apply {
            color = getPaintColor(progress)
            strokeWidth = stroke
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
        digitPaint = Paint().apply {
            color = getPaintColor(progress)
            strokeWidth = 2f
            style = Paint.Style.FILL_AND_STROKE
            setShadowLayer(5f, 0f, 0f, Color.DKGRAY)
            textSize = scaleSize
            typeface = Typeface.SANS_SERIF
            isAntiAlias = true
        }
        circlePaint = Paint().apply {
            color = Color.DKGRAY
            style = Paint.Style.FILL
        }
    }

    private fun getPaintColor(progress: Int): Int = when (progress) {
        in 0 .. 25 -> Color.parseColor("#e84258")
        in 26 .. 50 -> Color.parseColor("#fd8060")
        in 51 .. 75 -> Color.parseColor("#fee191")
        else -> Color.parseColor("#b0d8a4")
    }
}