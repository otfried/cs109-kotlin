//
// Light sensor test
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

class Main(val ctx: Context) : MiniApp {
  var light = 0.0

  init {
    ctx.setTitle("Light sensor demo")
    ctx.onLight { updateLight(it) }
  }

  fun updateLight(lx: Double) {
    if (lx != light) {
      light = lx
      ctx.update()
    }
  }

  override fun onDraw(canvas: Canvas) {
    val x = canvas.width / 2.0
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
    canvas.setFont(48.0)
    canvas.drawText("$light", x, 80.0, TextAlign.CENTER)
  }
}
