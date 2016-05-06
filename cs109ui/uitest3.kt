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

fun main(args: Array<String>) {
  setTitle("CS109 UI Keyboard Input Test")

  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  draw(image, Color.RED)
  show(image)

  println("Now press some keys inside the CS109 UI windows")
  println("Pressing 'q' will terminate the program")

  while (true) {
    val ch = waitKey()
    println("Got character $ch")
    if (ch == 'q')
      close()  // close window and terminate program
  }
}

