//
// Transparent drawing
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

fun draw(g: Canvas) {
  g.clear(Color.WHITE)
  
  g.setAlpha(128)  // 50% transparency
  g.setColor(Color.CYAN)
  g.drawCircle(200.0, 200.0, 150.0)
  g.setColor(Color.YELLOW)
  g.drawCircle(300.0, 200.0, 150.0)
  g.setColor(Color.MAGENTA)
  g.drawCircle(250.0, 300.0, 150.0)

  g.setAlpha(32)  // 12.5% transparency  
  g.setColor(Color(0, 128, 0)) // a darker green
  g.setFont(128.0)
  g.translate(g.width/2.0, g.height/2.0)
  g.rotate(-45.0)
  g.translate(0.0, 48.0)
  g.drawText("CS109", 0.0, 0.0, TextAlign.CENTER)
}
  
class Main(val ctx: Context) : MiniApp {
  init {
    ctx.setTitle("Drawing demo")
    ctx.onTap { x, y -> ctx.toast("You are tickling me!") }
  }

  override fun onDraw(canvas: Canvas) {
    draw(canvas)
  }
}
