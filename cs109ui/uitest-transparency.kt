import org.otfried.cs109ui.*
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

import java.awt.image.BufferedImage

fun draw(image: BufferedImage) {
  val g = ImageCanvas(image)
  g.clear(Color.WHITE)
  
  g.setAlpha(128)  // 50% transparency
  g.setColor(Color.CYAN)
  g.drawCircle(200.0, 200.0, 150.0)
  g.setColor(Color.YELLOW)
  g.drawCircle(300.0, 200.0, 150.0)
  g.setColor(Color.MAGENTA)
  g.drawCircle(250.0, 300.0, 150.0)

  g.setAlpha(32)  // 12.5% transparency  
  g.setColor(Color(0, 128, 0)) // a darker green
  g.setFont(128.0)
  g.translate(250.0, 250.0)
  g.rotate(-45.0)
  g.translate(0.0, 48.0)
  g.drawText("CS109", 0.0, 0.0, TextAlign.CENTER)

  g.done()
}

fun main(args: Array<String>) {
  setTitle("CS109 UI Transparency Test")
  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
  draw(image)
  show(image)
}
