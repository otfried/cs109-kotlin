package canvas

import org.otfried.cs109js.JsCanvas
import org.otfried.cs109.Color

import kotlin.browser.document
import org.w3c.dom.*

fun start() {
  println("Hello World from Javascript")

  val canvas = JsCanvas("canvas")
  canvas.clear(Color.GREEN)

  val text = document.getElementById("text")
  text?.appendChild(document.createTextNode("Was du hier liest ist kein Gedicht.")) 
}
