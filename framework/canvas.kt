package org.otfried.cs109

// --------------------------------------------------------------------

data class Color(val r: Int, val g: Int, val b: Int) {
  companion object {
    val WHITE = Color(255, 255, 255)
    val BLACK = Color(0, 0, 0)
    val RED = Color(255, 0, 0)
    val GREEN = Color(0, 255, 0)
    val BLUE = Color(0, 0, 255)
    val MAGENTA = Color(255, 0, 255)
    val CYAN = Color(0, 255, 255)
    val YELLOW = Color(255, 255, 0)
    val ORANGE = Color(255, 200, 0)
    val GRAY = Color(128, 128, 128)
    val DARK_GRAY = Color(64, 64, 64)
    val LIGHT_GRAY = Color(192, 192, 192)
    val PINK = Color(255, 175, 175)
  }
  constructor(rgb: Int) : 
    this(rgb shr 16, (rgb shr 8) and 0xff, rgb and 0xff) { }
}

enum class DrawStyle {
  STROKE, FILL, STROKE_AND_FILL
}

interface Canvas {
  val width: Int
  val height: Int

  fun clear(c: Color)

  fun setColor(c: Color)
  fun setAlpha(a: Int)
  fun setLineWidth(w: Double)
  fun setFont(size: Double, font: String = "sans-serif")

  fun drawRectangle(x: Double, y: Double, w: Double, h: Double,
                    s: DrawStyle = DrawStyle.FILL)
  fun drawCircle(x: Double, y: Double, r: Double,
                 s: DrawStyle = DrawStyle.FILL)
  fun drawText(text: String, x: Double, y: Double)

  fun textWidth(s: String): Double

  fun beginShape()
  fun moveTo(x: Double, y: Double)
  fun lineTo(x: Double, y: Double)
  fun closePath()
  fun drawShape(s: DrawStyle = DrawStyle.FILL)

  fun translate(x: Double, y: Double)
  fun rotate(degrees: Double)
  fun scale(sx: Double, sy: Double)
  fun save()
  fun restore()
}

// --------------------------------------------------------------------
