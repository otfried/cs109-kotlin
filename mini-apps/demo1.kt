//
// Smallest possible mini-app for the CS109 Android framework
//

import org.otfried.cs109.Color
import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp
import org.otfried.cs109.Canvas

class Main(context: Context) : MiniApp {
  var ctx: Context = context 

  init {
    ctx.setTitle("Demo #1")
  }

  override val name = "Demo1"
        
  override fun onDraw(canvas: Canvas) {
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.RED)
    canvas.drawCircle(canvas.width / 2.0, canvas.height / 2.0, 60.0)
  }
}
