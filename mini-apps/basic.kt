//
// Tiny mini-app for the CS109 Android framework
//

import org.otfried.cs109.Color
import org.otfried.cs109.TextAlign
import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp
import org.otfried.cs109.Canvas

class Main(val ctx: Context) : MiniApp {
  init {
    ctx.setTitle("Demo #1")
  }

  override fun onDraw(canvas: Canvas) {
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
    canvas.setFont(48.0)
    canvas.drawText("CS109", canvas.width / 2.0, 200.0, TextAlign.CENTER)
    canvas.drawCircle(canvas.width / 2.0, 400.0, 60.0)
  }
}
