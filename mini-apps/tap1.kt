//
// Tap demo
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

class Main(val ctx: Context) : MiniApp {
  private var lastX = 0.0
  private var lastY = 0.0

  init {
    ctx.setTitle("Tap and fling demo")
    ctx.onTap { x, y -> tapped(x, y) }
  }

  fun tapped(x: Double, y: Double) {
    lastX = x
    lastY = y
    ctx.update()
    println("Debugging: $x $y")
  }
  
  override fun onDraw(canvas: Canvas) {
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
    canvas.drawCircle(lastX, lastY, 30.0)
  }
}
