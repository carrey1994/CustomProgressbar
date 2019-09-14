package com.example.canvaspractice

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Thread.sleep

class HalfProgressBar : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var listener: LevelListener? = null
    private var paintBar = Paint()
    private var paintText = Paint()
    private var isGoal: Boolean = false
    private var progress: Float = 0F
    private var isStart: Boolean = false
    private var _goal: Float = 100F
    var goal: Float = _goal
        set(value) {
            _goal = value
            field = value
        }

    interface LevelListener {
        fun onInit()
        fun onGoal()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //initial setting
        paintBar.isAntiAlias = true
        paintText.isAntiAlias = true

        //set canvas position
        canvas!!.translate(width / 2.toFloat(), height / 2.toFloat())
        var rectF = RectF(-200F, -200F, 200F, 200F)
        canvas.save()

        //set pen bar style
        paintBar.color = Color.parseColor("#FFDFA0B5")
        paintBar.style = Paint.Style.STROKE
        paintBar.strokeCap = Paint.Cap.ROUND
        paintBar.strokeWidth = 35F

        //draw black bg
        paintBar.maskFilter = BlurMaskFilter(15F, BlurMaskFilter.Blur.SOLID)
        paintBar.style = Paint.Style.FILL_AND_STROKE
        paintBar.color = Color.parseColor("#000000")
        canvas.drawCircle(0F, 0F, 220F, paintBar)

        //set shader
        val shader = LinearGradient(
            -200F, -200F, 200F, 200F, Color.parseColor("#85B8CB"),
            Color.parseColor("#A13E96"), Shader.TileMode.CLAMP
        )
        paintBar.shader = shader

        //draw arc progress
        paintBar.maskFilter = BlurMaskFilter(25F, BlurMaskFilter.Blur.SOLID)
        paintBar.style = Paint.Style.STROKE
        val startAngle = 150F
        val sweepAngle = progress / 100 * 240 //max:240°C(100%)
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paintBar)

        //set goal pointer
        paintBar.shader = null
        paintBar.style = Paint.Style.STROKE
        paintBar.color = Color.WHITE
        paintBar.strokeWidth = 43F
        canvas.drawArc(rectF, startAngle + sweepAngle - 1, 1F, false, paintBar)

        //draw text
        //Sometime the typeface does not work on the other mobile device
        //not sure, the reason is because of SDK version or mobile device etc.. (not checked yet)
        paintText.color = Color.parseColor("#FFFFFF")
        paintText.maskFilter = BlurMaskFilter(5F, BlurMaskFilter.Blur.SOLID)
        var typeface = Typeface.createFromAsset(context.assets, "DS-DIGI.TTF")
        paintText.typeface = typeface
        paintText.textSize = 100F
        paintText.textAlign = Paint.Align.CENTER

        //the condition is for make sure the increasing progress won't be write on the canvas
        //because some threads are prepared start even the real progress value reaches the goal
        if (progress <= goal)
            canvas.drawText("${String.format("%.1f", progress)}\n%", 0F, 25F, paintText)
        else
            canvas.drawText("${String.format("%.1f", goal)}\n%", 0F, 25F, paintText)

        //call restore() is necessary for showing correct canvas ,otherwise the progress text will show inexactly.
        canvas.restore()

        if (progress + 0.1 > _goal) {
            listener?.onGoal()
            isGoal = true
        }

        setOnClickListener {
            if (isStart.not()) {
                listener?.onInit()
                isStart = true
            }
            //set Thread for progress redraw like animation
            Thread(Runnable {
                while (!isGoal) {
                    progress += 0.1f
                    postInvalidate()
                    // to draw faster or slower, there're two way,
                    // one is set the sec of thread sleeping,
                    // the other one is changing the increasing value of progress is also effective
                    sleep(1)
                }
            }).start()
        }
    }

    fun setListener(listener: LevelListener) {
        this.listener = listener
    }


}