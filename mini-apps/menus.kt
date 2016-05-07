//
// A menu for the mini-app
//

import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

class Main(val ctx: Context) : MiniApp {
  init {
    ctx.setTitle("Menu demo")
    ctx.createMenu(listOf(Pair("Toast", { ctx.toast("Well done!") } ),
                          Pair("Input", { askForName() } )))
  }

  fun askForName() {
    ctx.inputString("Tell me your name!") { ctx.setTitle("Hello $it") }
  }

  override fun onDraw(canvas: Canvas) {
    val x = canvas.width / 2.0
    canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
    canvas.setFont(32.0)
    canvas.drawText("Open the menu", x, 50.0, TextAlign.CENTER)
  }
}
