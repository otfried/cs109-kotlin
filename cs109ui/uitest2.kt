import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

import java.awt.image.BufferedImage

fun draw(image: BufferedImage, color: Color) {
  val g = ImageCanvas(image)
  g.clear(Color.WHITE)
  g.setColor(color)
  g.drawRectangle(100.0, 100.0, 300.0, 300.0)
  g.done()
}

fun showWait(image: BufferedImage, color: Color, ms: Int) {
  draw(image, color)  // draw rectangle
  show(image)
  waitForMs(ms)       // wait ms milliseconds
}

fun main(args: Array<String>) {
  setTitle("CS109 UI Blinking Rectangle")

  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  showWait(image, Color.WHITE, 500)  // 0.5 sec white picture
  showWait(image, Color.RED, 1000)   // 1 sec red rectangle
  showWait(image, Color.WHITE, 500)  // 0.5 sec white picture
  showWait(image, Color.BLUE, 1000)  // 1 sec blue rectangle
  showWait(image, Color.WHITE, 5000) // 5 secs white picture  
  
  close() // close window and terminate program
}
