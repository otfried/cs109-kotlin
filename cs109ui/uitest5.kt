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
  setTitle("CS109 UI Timer Test")

  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  draw(image, Color.RED)
  show(image)

  println("You have 5 seconds to press a key inside the CS109 UI window")

  setTimeOut(5000)
  val ch = waitKey()
  if (ch == timeOutChar)
    println("You lost!")
  else
    println("You won by typing $ch")

  close()  // close window and terminate program
}
