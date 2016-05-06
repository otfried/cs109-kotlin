package canvas

import kotlin.browser.document
import kotlin.browser.window
import org.w3c.dom.*
import org.w3c.dom.events.*

object Canvas {
  val canvas = document.getElementById("canvas") as HTMLCanvasElement
  val ctx = canvas.getContext("2d")!! as CanvasRenderingContext2D
  init {
    canvas.width = window.innerWidth.toInt() - 20
    canvas.height = window.innerHeight.toInt() - 50
  }

  var x = canvas.width / 2.0
  var y = canvas.height / 2.0
  var alpha = 0.0
  var animate = false
  var timeStamp = 0.0
  
  fun draw() {
    ctx.save()
    ctx.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
    ctx.fillStyle = "green"
    ctx.globalAlpha = 0.2
    ctx.fillRect(10.0, 10.0, 100.0, 100.0)
    ctx.globalAlpha = 1.0
    for (i in 0 .. 5) {
      for (j in 0 .. 5) {
        ctx.fillStyle = "rgb(${Math.floor(255-42.5*i)},${Math.floor(255-42.5*j)},0)"
        ctx.fillRect(150.0 + j*25.0, i*25.0, 25.0, 25.0)
      }
    }
    ctx.translate(x, y)
    ctx.globalAlpha = 0.5
    ctx.rotate(alpha)
    ctx.fillStyle = "red"
    ctx.font = "32px sans-serif"
    ctx.fillText("Lovely", 0.0, 0.0)
    ctx.restore()
    if (animate)
      window.requestAnimationFrame { animate(it) }
  }
}

fun animate(s: Double) {
  val delta = s - Canvas.timeStamp
  Canvas.timeStamp = s
  Canvas.x += delta / 2.0
  if (Canvas.x > Canvas.canvas.width)
    Canvas.x = 0.0
  Canvas.alpha += Math.PI / 360.0 * delta
  if (Canvas.alpha > 2 * Math.PI)
    Canvas.alpha = 0.0
  Canvas.draw()
}

fun keyDown(e: Event) {
  val ek = e as KeyboardEvent
  var k = ek.key
  if (k === undefined)
    k = "${ek.keyCode.toChar().toLowerCase()}"
  when (k) {
  "a" -> Canvas.x -= 3
  "s" -> Canvas.x += 3
  "w" -> Canvas.y -= 3
  "z" -> Canvas.y += 3
  "j" -> Canvas.alpha += Math.PI / 36.0
  "k" -> Canvas.alpha -= Math.PI / 36.0
  "g" -> {
      if (!Canvas.animate)
      	Canvas.timeStamp = window.performance.now()
      Canvas.animate = !Canvas.animate
  }
  else -> return
  }
  Canvas.draw()
  e.preventDefault()
}

fun mouseDown(e: Event) {
  val em = e as MouseEvent
  Canvas.x = em.offsetX
  Canvas.y = em.offsetY
  Canvas.draw()
}

fun start() {
  println("Canvas4 starting...")
  println("Active keys are aswzjk and g for animation")
  Canvas.draw()
  window.addEventListener("keydown", { keyDown(it) }, true)
  window.addEventListener("mousedown", { mouseDown(it) }, true)
}
