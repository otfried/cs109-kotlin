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

fun showSleep(image: BufferedImage, color: Color, ms: Long) {
  draw(image, color)       // draw rectangle
  show(image)
  Thread.sleep(ms)          // wait ms milliseconds
}

fun main(args: Array<String>) {
  setTitle("CS109 UI Blinking Rectangle")

  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  showSleep(image, Color.WHITE, 500L) // 0.5 sec white picture
  showSleep(image, Color.RED, 1000L)  // 1 sec red rectangle
  showSleep(image, Color.WHITE, 500L) // 0.5 sec white picture
  showSleep(image, Color.BLUE, 1000L) // 1 sec blue rectangle
  showSleep(image, Color.WHITE, 5000L) // 5 secs white picture  
  
  close() // close window and terminate program
}
