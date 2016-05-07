//
// Drawing and toast
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

fun draw(g: Canvas) {
  // clear background
  g.clear(Color.WHITE)
  
  // draw two filled circles
  g.setColor(Color.RED)
  g.drawCircle(50.0, 50.0, 20.0)  // FILL is the default
  g.setColor(Color.ORANGE)
  g.drawCircle(250.0, 400.0, 20.0)
  
  // draw an unfilled circle with a pen of width 3
  g.setColor(Color.MAGENTA)
  g.setLineWidth(3.0)
  g.drawCircle(415.0, 50.0, 15.0, DrawStyle.STROKE)
    
  // draw a filled and an unfilled Rectangle
  g.setColor(Color.CYAN)
  g.drawRectangle(20.0, 400.0, 50.0, 20.0, DrawStyle.FILL)
  g.drawRectangle(400.0, 400.0, 50.0, 20.0, DrawStyle.STROKE)
  
  // draw a line
  g.setLineWidth(1.0)   // reset to default
  g.setColor(Color(0, 0, 255)) // same as Color.BLUE
  g.beginShape()
  g.moveTo(50.0, 50.0)
  g.lineTo(250.0, 400.0)
  g.drawShape(DrawStyle.STROKE)
  
  // draw a non-convex quadrilateral:
  g.save()              // save current coordinate system
  g.translate(360.0, 260.0) // move origin to here
  g.rotate(-30.0)           // rotate 30 degrees counter-clockwise
  g.beginShape()
  g.moveTo(0.0, 0.0)
  g.lineTo(30.0, -40.0)
  g.lineTo(60.0, 0.0)
  g.lineTo(30.0, -100.0)
  g.closePath()
  g.drawShape()
  g.restore()           // restore current coordinate system
  
  // draw some text
  g.setColor(Color(0, 128, 0)) // a darker green
  g.setFont(20.0, "Batang")
  g.drawText("Hello World!", 155.0, 225.0)
  g.drawText("안녕 하세요", 175.0, 245.0)
}
  
class Main(val ctx: Context) : MiniApp {
  init {
    ctx.setTitle("Drawing demo")
    ctx.onTap { x, y -> ctx.toast("You are tickling me!") }
  }

  override fun onDraw(canvas: Canvas) {
    draw(canvas)
  }
}
