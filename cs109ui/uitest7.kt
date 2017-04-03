import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

import org.otfried.cs109ui.TimeOut
import org.otfried.cs109ui.Key
import org.otfried.cs109ui.Mouse

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

  println("You have 5 seconds to press a key or click the mouse")

  setTimeOut(5000)
  val ev = waitEvent()
  when (ev) {
    is TimeOut -> 
      println("You lost!")
    is Key ->
      println("You won by typing ${ev.ch}")
    is Mouse ->
      println("You won by clicking at (${ev.x}, ${ev.y})")
  }
  close()  // close window and terminate program
}
