package com.example.canvaspractice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.lang.StringBuilder
import kotlin.concurrent.thread
import kotlin.random.Random

class CustomView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var paint = Paint()

    var progress: Float = 0F

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
        paint.isAntiAlias = true

//        var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.img)
//        var shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//        paint.shader = shader
//        var radius: Int
//        if (bitmap.height > bitmap.width)
//            radius = bitmap.width/2
//        else
//            radius = bitmap.height/2
//        canvas!!.drawCircle(bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2, radius.toFloat(), paint)

//        paint.color = Color.parseColor("#111111")
//        var pathEffect = DiscretePathEffect(100F,30F)
//        paint.setPathEffect(pathEffect)
//        var path = Path()
//        path.lineTo(200F,400F)
//        path.lineTo(400F,600F)
//        canvas!!.drawPath(path, paint)

//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 50F
//        canvas!!.drawOval(200F, 300F, 500F, 1000F, paint)
//        for (i in 0..19){
//            paint.color = Color.parseColor(randonColor())
//            canvas!!.drawOval(getRandNum(), getRandNum(), getRandNum(), getRandNum(), paint)
//        }

//        paint.color = Color.parseColor(randonColor())
//        canvas!!.drawArc(100F, 200F,900F, 1000F,0F, 125F, true, paint)
//        paint.color = Color.parseColor(randonColor())
//        canvas!!.drawArc(100F, 200F,900F, 1000F, 125F, 180F, true, paint)
//        paint.color = Color.parseColor(randonColor())
//        canvas!!.drawArc(100F, 200F,900F, 1000F, 0F, -60F, true, paint)
//        paint.color = Color.parseColor(randonColor())
//        canvas!!.drawArc(100F, 200F,900F, 1000F, -60F, -150F, true, paint)
//        paint.color = Color.parseColor(randonColor())
//        canvas!!.drawArc(100F, 200F,900F, 1000F, -150F, -180F, true, paint)

//        var shader = LinearGradient(
//            100F, 100F, 500F, 500F, Color.parseColor("#E91E63"),
//            Color.parseColor("#2196F3"), Shader.TileMode.CLAMP
//        );
//        paint.setShader(shader)
//        canvas!!.drawCircle(300F, 300F, 200F, paint)

//        var rectF = RectF(100F, 300F, 700F, 900F)
//        canvas!!.drawArc(rectF, 100F,100F, false, paint)

        var color = arrayOf(randonColor(), randonColor(), randonColor(), randonColor())
        var data = arrayOf(30, 40, 50, 90)
        var rectF = RectF(-150F, -150F, 150F, 150F)
        var startAngle = -90

        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 30F

        canvas!!.save()
        canvas.translate(width / 2.toFloat(), height / 2.toFloat())

        paint.color = Color.argb(20, 33, 33, 33)
        canvas.drawCircle(0F, 0F, 150F, paint)

        for (i in 0 until color.size) {
            paint.color = Color.parseColor(color[i])
            canvas.drawArc(rectF, startAngle.toFloat(), data[i].toFloat(), false, paint)
            startAngle += data[i]
        }

        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5F
        paint.textSize = 70F
        paint.color = Color.parseColor("#000000")
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("90%", 0F, 30F, paint)



    }

    fun getRandNum(): Float {
        var num = (1..width).random()
        return num.toFloat()
    }

    fun randonColor(): String {
        var colorCode = StringBuilder()
        var selection = arrayOf("A", "B", "C", "D", "E", "F", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        for (i in 1..6) {
            var x = (0..15).random()
            colorCode.append(selection[x])
        }
        return "#" + colorCode.toString()
    }

}