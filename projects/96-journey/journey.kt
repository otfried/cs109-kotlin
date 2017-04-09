// Javascript version of chicken.kt

package journey

import org.otfried.cs109js.JsCanvas

import kotlin.browser.window
import org.w3c.dom.events.*

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

// Original graphic by Jeong-eun Yu and Geum-hyeon Song

val speed = 60 // ms

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

object Controller {
  val canvas = JsCanvas("canvas")
  val hen = Chicken(true, 600.0, 200.0)
  val chick1 = Chicken(false, 720.0, 210.0)
  val chick2 = Chicken(false, 800.0, 210.0)
  val herd = listOf(hen, chick1, chick2)

  var timeStamp = 0.0
  var tick = 0

  init {
    canvas.setFont(30.0)
  }    

  fun drawImage() {
    canvas.clear(Color(173,216,230))
    canvas.setColor(Color(144,238,144))
    canvas.drawRectangle(0.0, 200.0, 1000.0, 150.0)
    canvas.setColor(Color.ORANGE)
    canvas.drawCircle(100.0, 50.0, 40.0)
    for (chicken in herd)
      chicken.draw(canvas)
  }

  fun draw() {
    drawImage()
    if (tick in 0..160) {
      if (tick in 60..79)
        canvas.drawText("OH!", 800.0, 160.0, TextAlign.CENTER)
      if (tick in 80..109)
        canvas.drawText("Where is my mommy going?",
                        550.0, 110.0, TextAlign.CENTER)    
      for (chicken in listOf(hen, chick1)) {
	chicken.x -= 5
	chicken.y += if (tick % 2 == 0) -2 else 2
      }
    }

    if (tick in 110 .. 209) { 
      canvas.drawText("Wait for me!", 500.0, 110.0, TextAlign.CENTER)    
      chick2.x -= 10
      chick2.y += if (tick % 10 < 5) -20 else 20
      chick2.flap(if (tick % 10 < 5) -10.0 else 10.0)
    }
  }

  fun animate(s: Double) {
    val delta = s - timeStamp
    if (delta > speed) {
      timeStamp = s
      tick += 1
      draw()
    }
    if (tick < 210)
      window.requestAnimationFrame { animate(it) }
  }

  // start animation on mouse click
  fun mouseDown(e: Event) {
    tick = 0
    hen.x = 600.0
    chick1.x = 720.0
    chick2.x = 800.0
    timeStamp = window.performance.now()
    window.requestAnimationFrame { animate(it) }
  }
}

fun start() {
  Controller.draw()
  window.addEventListener("mousedown", { Controller.mouseDown(it) }, true)
}

// --------------------------------------------------------------------
