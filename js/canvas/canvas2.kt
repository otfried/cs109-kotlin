package canvas

import org.otfried.cs109js.JsCanvas
import org.otfried.cs109.Color

object Controller {
  val canvas = JsCanvas("canvas")
  var x = 30.0
  var y = 50.0
  var alpha = 45.0
  
  fun draw() {
    canvas.clear(Color.WHITE)
    canvas.setAlpha(48)
    canvas.setColor(Color.GREEN)
    canvas.drawRectangle(10.0, 10.0, 100.0, 100.0)
    canvas.setAlpha(255) // opaque
    for (i in 0 .. 5) {
      for (j in 0 .. 5) {
        canvas.setColor(Color(Math.floor(255-42.5*i), Math.floor(255-42.5*j), 0))
        canvas.drawRectangle(150.0 + j*25.0, i*25.0, 25.0, 25.0)
      }
    }
    canvas.translate(x, y)
    canvas.setAlpha(128)
    canvas.rotate(alpha)
    canvas.setColor(Color.RED)
    canvas.setFont(32.0)
    canvas.drawText("Lovely", 0.0, 0.0)
  }
}

fun start() {
  println("Canvas2 starting...")
  Controller.draw()
}
