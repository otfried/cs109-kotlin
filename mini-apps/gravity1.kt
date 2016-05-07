//
// Gravity sensor test #1
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

fun sq(x: Double) = x * x

class Main(val ctx: Context) : MiniApp {
  var gravity = arrayOf(0.0, 0.0, 0.0)

  init {
    ctx.setTitle("Gravity sensor demo #1")
    ctx.onGravity { x, y, z -> updateGravity(x, y, z) }
  }

  fun updateGravity(x: Double, y: Double, z: Double) {
    gravity = arrayOf(x, y, z)
    ctx.update()
  }

  override fun onDraw(canvas: Canvas) {
    val x = canvas.width / 2.0
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
    canvas.setFont(48.0)
    for (i in 0..2)
      canvas.drawText("%.3f".format(gravity[i]), x, 80.0 + i * 60.0, 
                      TextAlign.CENTER)
    val norm = Math.sqrt(sq(gravity[0]) + sq(gravity[1]) + sq(gravity[2]))
    canvas.drawText("%.3f".format(norm), x, 300.0, TextAlign.CENTER)
  }
}
