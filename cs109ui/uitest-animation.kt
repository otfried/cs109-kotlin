import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

import java.awt.image.BufferedImage

fun draw(image: BufferedImage, x: Double, y: Double) {
  val g = ImageCanvas(image)
  g.clear(Color.WHITE)
  g.setColor(Color.RED)
  g.drawCircle(x, y, 40.0)
  g.done()
}

fun main(args: Array<String>) {
  setTitle("CS109 UI Animation test")

  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  var x = 30.0
  var y = 30.0
  while (x < 500.0) {
    draw(image, x, y)
    x += 2
    y += 1
    show(image)
    Thread.sleep(10)
  }
}
