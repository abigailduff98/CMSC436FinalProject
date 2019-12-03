package project.readaloud

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.Bitmap
import android.text.method.TextKeyListener.clear



class PaintCanvasView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var paint: Paint
    private var path: Path
    private lateinit var mCanvas: Canvas

    init {
        paint = Paint()
        path = Path()
        paint.isAntiAlias = true
        paint.color = Color.BLUE
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val xPos = event.x
        val yPos = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(xPos, yPos)
                return true
            }
            MotionEvent.ACTION_MOVE -> path.lineTo(xPos, yPos)
            MotionEvent.ACTION_UP -> {
            }
            else -> return false
        }

        invalidate()
        return true
    }

    fun setBitmap(bitmap: Bitmap?) {
        if (bitmap == null) {
            return
        }

        this.mCanvas.drawBitmap(bitmap, 0.toFloat(), 0.toFloat(), this.paint)
        invalidate()
    }

    fun getBitmap(): Bitmap? {
        if (mCanvas == null) {
            return null
        }
        this.isDrawingCacheEnabled = true
        this.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(this.drawingCache)
        this.isDrawingCacheEnabled = false
        return bitmap
    }

}
