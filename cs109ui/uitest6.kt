import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

import java.awt.image.BufferedImage

fun draw(image: BufferedImage) {
  val g = ImageCanvas(image)
  g.clear(Color.WHITE)
  g.setColor(Color.RED)
  g.drawRectangle(50.0, 150.0, 150.0, 150.0)
  g.setColor(Color.GREEN)
  g.drawRectangle(300.0, 150.0, 150.0, 150.0)
  g.done()
}

fun main(args: Array<String>) {
  setTitle("CS109 UI Timer Test")

  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  draw(image)
  show(image)

  println("You have 3 seconds to click a square")

  setTimeOut(3000)
  val (x, y) = waitMouse()

  if (x < 0)
    println("You are too slow.")
  else 
    println("You clicked at $x $y")

  close()  // close window and terminate program
}
