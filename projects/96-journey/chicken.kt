import org.otfried.cs109ui.*
import java.awt.image.BufferedImage

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

// Original graphic by Jeong-eun Yu and Geum-hyeon Song

val speed = 60L
val magnify = 1.0 // to enlarge animation on hires screen

// --------------------------------------------------------------------

// helper function
fun drawEllipse(g: Canvas, x: Double, y: Double, rx: Double, ry: Double,
                fill: Color, stroke: Color) {
  g.save()
  g.translate(x, y)		  
  g.scale(1.0, ry / rx)
  g.setColor(fill)		  
  g.drawCircle(0.0, 0.0, rx, DrawStyle.FILL)
  g.setColor(stroke)
  g.drawCircle(0.0, 0.0, rx, DrawStyle.STROKE)
  g.restore()
}

class Chicken(val hen: Boolean, var x: Double, var y: Double) {

  private var wingAngle = 0.0
  
  // flap my wing
  fun flap(angle: Double) {
    wingAngle += angle
  }

  // draw myself on the canvas g
  fun draw(g: Canvas) {
    g.save()
    g.translate(x, y)
    if (hen) {
      // beak
      g.save()
      g.translate(-36.0, 0.0)
      g.rotate(45.0)
      g.setColor(Color.ORANGE)
      g.drawRectangle(-4.0, -4.0, 8.0, 8.0, DrawStyle.STROKE_AND_FILL)
      g.restore()
      // body
      drawEllipse(g, 0.0, 0.0, 35.0, 40.0, Color.WHITE, Color.YELLOW)
      // wing
      drawEllipse(g, 15.0, 20.0, 30.0, 20.0, Color.WHITE, Color.YELLOW)
      // eye
      g.setColor(Color.BLACK)
      g.drawCircle(-15.0, -15.0, 3.0)
      // dots on head
      drawEllipse(g, 0.0, -42.0, 2.5, 4.0, Color.RED, Color.RED)
      drawEllipse(g, -6.0, -42.0, 2.5, 4.0, Color.RED, Color.RED)      
    } else {
      // beak
      g.save()
      g.translate(-22.0, 0.0)
      g.rotate(45.0)
      g.setColor(Color.ORANGE)
      g.drawRectangle(-2.0, -2.0, 4.0, 4.0, DrawStyle.STROKE_AND_FILL)
      g.restore()
      // body
      drawEllipse(g, 0.0, 0.0, 20.0, 25.0, Color.YELLOW, Color.YELLOW)
      // wing
      g.save()
      g.translate(5.0, 10.0)
      g.rotate(wingAngle)
      drawEllipse(g, 5.0, 5.0, 15.0, 10.0, Color.YELLOW, Color.ORANGE)
      g.restore()
      // eye
      g.setColor(Color.BLACK)
      g.drawCircle(-5.0, -10.0, 2.0)
    }
    g.restore()
  }
}

// --------------------------------------------------------------------

fun drawImage(g: Canvas, herd: List<Chicken>) {
  g.clear(Color(173,216,230))
  g.setColor(Color(144,238,144))
  g.drawRectangle(0.0, 200.0, 1000.0, 150.0)
  g.setColor(Color.ORANGE)
  g.drawCircle(100.0, 50.0, 40.0)
  for (chicken in herd)
    chicken.draw(g)
}

// --------------------------------------------------------------------

fun main(args: Array<String>) {
  setTitle("Journey of Chicken")
  val image = BufferedImage((1000 * magnify).toInt(),
      	      		    (350 * magnify).toInt(),
			    BufferedImage.TYPE_INT_RGB)
  val canvas = ImageCanvas(image)

  val hen = Chicken(true, 600.0, 200.0)
  val chick1 = Chicken(false, 720.0, 210.0)
  val chick2 = Chicken(false, 800.0, 210.0)
  val herd = listOf(hen, chick1, chick2)
  canvas.setFont(30.0)
  canvas.scale(magnify, magnify)  // hires screen
  
  for (t in 0..160) {
    drawImage(canvas, herd)
    if (60 <= t && t < 80)
      canvas.drawText("OH!", 800.0, 160.0, TextAlign.CENTER)
    if (80 <= t && t < 110)
      canvas.drawText("Where is my mommy going?",
                      550.0, 110.0, TextAlign.CENTER)    
    show(image)
    for (chicken in listOf(hen, chick1)) {
      chicken.x -= 5
      chicken.y += if (t % 2 == 0) -2 else 2
    }
    Thread.sleep(speed)
  }

  for (t in 0 until 100) { 
    drawImage(canvas, herd)
    canvas.drawText("Wait for me!", 500.0, 110.0, TextAlign.CENTER)    
    show(image)
    chick2.x -= 10
    chick2.y += if (t % 10 < 5) -20 else 20
    chick2.flap(if (t % 10 < 5) -10.0 else 10.0)
    Thread.sleep(speed)
  }
  
  while (true) {
    val ch = waitKey()
    if (ch == 'x')
      close()
  }
}

// --------------------------------------------------------------------
