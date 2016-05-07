//
// Gravity sensor test #2
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

fun sq(x: Double) = x * x

class Main(val ctx: Context) : MiniApp {
  var angle: Double? = null

  init {
    ctx.setTitle("Gravity sensor demo #2")
    ctx.onGravity { x, y, z -> updateGravity(x, y, z) }
  }

  fun updateGravity(x: Double, y: Double, z: Double) {
    if (Math.abs(z) > 2.0) {
      angle = null
    } else {
      val n = Math.sqrt(x*x + y*y)
      val x0 = x/n
      val y0 = y/n
      angle = Math.atan2(x0, y0)
    }    
    ctx.update()
  }

  override fun onDraw(canvas: Canvas) {
    val x = canvas.width / 2.0
    val y = canvas.height / 2.0
    canvas.translate(x, y)
    val w = (canvas.width + canvas.height).toDouble()
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
    canvas.setFont(48.0)
    val a = angle
    if (a == null) {
      canvas.drawText("Hold phone vertical", 0.0, 0.0, TextAlign.CENTER)
    } else {
      canvas.rotate(a / Math.PI * 180.0)    
      canvas.beginShape()
      canvas.moveTo(-w, 0.0)
      canvas.lineTo(-w, w)
      canvas.lineTo(w, w)
      canvas.lineTo(w, 0.0)
      canvas.closePath()
      canvas.drawShape()
    }
  }
}
