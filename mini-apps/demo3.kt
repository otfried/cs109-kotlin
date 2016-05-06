//
// Drawing and toast
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

class Main(val ctx: Context) : MiniApp {
  private var lastX = 0.0
  private var lastY = 0.0
  private var lastT = 0
  private var flingDir = 0.0

  init {
    ctx.setTitle("Demo #3")
    ctx.onTap { x, y -> tapped(x, y, 1) }
    ctx.onDoubleTap { x, y -> tapped(x, y, 2) }
    ctx.onPress { x, y -> tapped(x, y, 3) }
    ctx.onFling { x, y, dir, d -> flinged(dir) }
  }

  fun tapped(x: Double, y: Double, t: Int) {
    lastX = x
    lastY = y
    lastT = t
    ctx.update()
  }

  fun flinged(dir: Char) {
    when(dir) {
    'l' -> flingDir = 270.0
    'd' -> flingDir = 180.0
    'r' -> flingDir = 90.0
    else -> flingDir = 0.0
    }
    lastT = 4
    ctx.update()
  }
  

  override fun onDraw(canvas: Canvas) {
    canvas.clear(Color(255, 255, 192))
    if (lastT == 0)
      return
    canvas.setColor(Color.BLUE)
    canvas.setFont(32.0)
    canvas.drawText(when(lastT) {
      1 -> "tap"
      2 -> "double tap"
      3 -> "press"
      4 -> "fling"
      else -> ""
      }, canvas.width / 2.0, 80.0, TextAlign.CENTER)
    if (lastT != 4)
      canvas.drawCircle(lastX, lastY, 30.0)
    else {
      canvas.translate(canvas.width / 2.0, canvas.height / 2.0)
      canvas.rotate(flingDir)
      canvas.beginShape()
      canvas.moveTo(-30.0, 0.0)
      canvas.lineTo(0.0, -40.0)
      canvas.lineTo(30.0, 0.0)
      canvas.lineTo(0.0, -100.0)
      canvas.closePath()
      canvas.drawShape()
    }
  }
}
