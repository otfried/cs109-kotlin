package org.otfried.cs109js

import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign
import org.otfried.cs109.Canvas
	
import kotlin.browser.document
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.CanvasRenderingContext2D

// --------------------------------------------------------------------

class JsCanvas(id: String) : Canvas {
  val canvas = document.getElementById(id) as HTMLCanvasElement
  val ctx = canvas.getContext("2d")!! as CanvasRenderingContext2D

  private var color = Color.BLACK
      
  private fun cols(c: Color): String = "rgb(${c.r},${c.g},${c.b})"
  private fun toRadians(degrees: Double) = degrees / 180.0 * Math.PI

  override val width: Int
    get() = canvas.width
  override val height: Int
    get() = canvas.height

  override fun clear(c: Color) {
    ctx.fillStyle = cols(c)
    ctx.fillRect(0.0, 0.0, width.toDouble(), height.toDouble())
  }

  override fun setColor(c: Color) { color = c }
  override fun setAlpha(a: Int) { ctx.globalAlpha = a.toDouble() / 255.0 }
  override fun setLineWidth(w: Double) { ctx.lineWidth = w }
  override fun setFont(size: Double, font: String) { 
    ctx.font = "${size}px $font"
  }

  override fun drawRectangle(x: Double, y: Double, 
                             w: Double, h: Double, s: DrawStyle) {
    if (s != DrawStyle.STROKE) {
      ctx.fillStyle = cols(color)
      ctx.fillRect(x, y, w, h)
    }
    if (s != DrawStyle.FILL) {
      ctx.strokeStyle = cols(color)
      ctx.strokeRect(x, y, w, h)
    }
  }

  override fun drawCircle(x: Double, y: Double, r: Double, s: DrawStyle) {
    ctx.beginPath()
    ctx.arc(x, y, r, 0.0, 2*Math.PI, false)
    ctx.closePath()
    drawShape(s)
  }

  override fun drawText(text: String, x: Double, y: Double, align: TextAlign) {
    ctx.fillStyle = cols(color)
    ctx.textAlign = align.toString().toLowerCase()
    ctx.fillText(text, x, y)
  }

  override fun textWidth(s: String): Double = ctx.measureText(s).width

  override fun beginShape() { ctx.beginPath() }
  override fun moveTo(x: Double, y: Double) { ctx.moveTo(x, y) }
  override fun lineTo(x: Double, y: Double) { ctx.lineTo(x, y) }
  override fun closePath() { ctx.closePath() }
  override fun drawShape(s: DrawStyle) {
    if (s != DrawStyle.STROKE) {
      ctx.fillStyle = cols(color)
      ctx.fill()
    }
    if (s != DrawStyle.FILL) {
      ctx.strokeStyle = cols(color)
      ctx.stroke()
    }
  }

  override fun translate(x: Double, y: Double) { ctx.translate(x, y) }
  override fun rotate(degrees: Double) { ctx.rotate(toRadians(degrees)) }
  override fun scale(sx: Double, sy: Double) { ctx.scale(sx, sy) }
  override fun save() { ctx.save() }
  override fun restore() { ctx.restore() }
}

// --------------------------------------------------------------------
