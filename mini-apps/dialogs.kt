//
// Different dialogs
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

class Main(val ctx: Context) : MiniApp {
  init {
    ctx.setTitle("Dialog demo")
    ctx.onTap { x, y -> tapped(x, y) }
  }

  fun tapped(x: Double, y: Double) {
    if (y < 100)
      ctx.toast("This is a toast!")
    else if (y < 200)
      ctx.showMessage("This is a message.  I can tell you a lot!")
    else if (y < 300)
      ctx.askYesNo("This is a question. Do you know the answer?") {
        answer -> if (answer) ctx.toast("Good!") }
    else
      ctx.inputString("Tell me your name!") {
        s -> ctx.setTitle("Hello $s") }
  }

  override fun onDraw(canvas: Canvas) {
    val x = canvas.width / 2.0
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
    canvas.setFont(32.0)
    canvas.drawText("Toast", x, 50.0, TextAlign.CENTER)
    canvas.drawText("Message", x, 150.0, TextAlign.CENTER)
    canvas.drawText("Yes or No", x, 250.0, TextAlign.CENTER)
    canvas.drawText("Name", x, 350.0, TextAlign.CENTER)
  }
}
