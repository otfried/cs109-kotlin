import org.otfried.cs109ui.*
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

import java.awt.image.BufferedImage

fun draw(image: BufferedImage, ntime: String) {
  val g = ImageCanvas(image)
  g.clear(Color.WHITE)
  g.setColor(Color(128, 0, 0))
  g.setFont(100.0)
  g.drawText(ntime, 250.0, 100.0, TextAlign.CENTER)
  g.done()
}

fun main(args: Array<String>) {
  setTitle("CS109 Clock")

  val image = BufferedImage(500, 120, BufferedImage.TYPE_INT_RGB)
  val form = java.text.SimpleDateFormat("HH:mm:ss")
  var current = form.format(java.util.Calendar.getInstance().getTime())

  while (true) {
    Thread.sleep(100)
    val ntime = form.format(java.util.Calendar.getInstance().getTime())
    if (ntime != current) {
      current = ntime
      draw(image, current)
      show(image)
    }
  }
}
