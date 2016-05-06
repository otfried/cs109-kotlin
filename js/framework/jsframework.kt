package jsframework

import kotlin.browser.document
import kotlin.browser.window
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent

import org.otfried.cs109js.*
import org.otfried.cs109.Context
import org.otfried.cs109.Canvas

object Controller : Context {
  val canvas = JsCanvas("canvas")

  val app = Main(this)

  fun draw() {
    app.onDraw(canvas)
  }

  fun mouseDown(e: Event) {
    val em = e as MouseEvent
    app.onTouch(em.offsetX, em.offsetY)
  }
  
  // ------------------------------------------------------------------

  override fun update() { draw() }
  override fun toast(s: String) {
    window.alert(s)
  }
  override fun setTitle(s: String) {
    document.title = s
  }
  override fun showMessage(s: String) {
    window.alert(s)
  }
  override fun askYesNo(s: String, handle: (Boolean) -> Unit) {
    handle(window.confirm(s))
  }
  override fun inputString(prompt: String, handle: (String) -> Unit) {
    val r = window.prompt(prompt)
    if (r != null)
      handle(r)
  }   
}

fun start() {
  Controller.draw()
  window.addEventListener("mousedown", { Controller.mouseDown(it) }, true)
}
