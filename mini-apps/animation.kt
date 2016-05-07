//
// Animation with "after"
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

class Main(val ctx: Context) : MiniApp {
  var y: Double = 0.0
  var dead = false

  init {
    ctx.setTitle("Animation demo")
    ctx.onFling { x0, y0, dir, dist -> if (dir == 'u') y = 0.0; ctx.update() }
    ctx.after(20) { animate() }
  }

  fun animate() {
    y += 6.0
    ctx.update()
    if (y > ctx.height)
      dead = true
    else
      ctx.after(20) { animate() }
  }

  override fun onDraw(canvas: Canvas) {
    if (dead) {
      canvas.clear(Color(172, 0, 0)) // blood-red
    } else {    
      val x = canvas.width / 2.0
      canvas.clear(Color(255, 255, 192))
      canvas.setColor(Color.BLUE)
      canvas.drawCircle(x, y, 30.0)
    }
  }
}
