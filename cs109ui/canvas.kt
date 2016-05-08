package org.otfried.cs109ui

import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign
import org.otfried.cs109.Canvas

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Color as JColor
import java.awt.BasicStroke
import java.awt.AlphaComposite       
import java.awt.geom.*
	
// --------------------------------------------------------------------

class ImageCanvas(val img: BufferedImage) : Canvas {
  private val g = img.createGraphics()
  private val ctm = mutableListOf<AffineTransform>()
  private var path: Path2D.Double? = null

  init {
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
		       java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
  }

  private fun draw(s: DrawStyle, shape: java.awt.Shape) {
    if (s != DrawStyle.STROKE)
      g.fill(shape)
    if (s != DrawStyle.FILL)
      g.draw(shape)
  }

  private fun toRadians(degrees: Double) = degrees / 180.0 * Math.PI


  override val width: Int
    get() = img.width
  override val height: Int
    get() = img.height

  override fun clear(c: Color) {
    g.setColor(JColor(c.r, c.g, c.b))
    g.fillRect(0, 0, width, height)
  }

  override fun setColor(c: Color) {
    g.setColor(JColor(c.r, c.g, c.b))
  }
  
  override fun setAlpha(a: Int) {
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                   (a / 255f)))
  }
  
  override fun setLineWidth(w: Double) {
    g.setStroke(BasicStroke(w.toFloat()))
  }

  override fun setFont(size: Double, font: String) {
    g.setFont(java.awt.Font(font, java.awt.Font.PLAIN, size.toInt()))
  }

  override fun drawRectangle(x: Double, y: Double, w: Double, h: Double,
                             s: DrawStyle) {
    draw(s, Rectangle2D.Double(x, y, w, h))			    
  }
  
  override fun drawCircle(x: Double, y: Double, r: Double, s: DrawStyle) {
    draw(s, Ellipse2D.Double(x-r, y-r, 2*r, 2*r))
  }
  
  override fun drawText(text: String, x: Double, y: Double, align: TextAlign) {
    var x0 = x.toFloat()
    if (align != TextAlign.LEFT) {
      val w = g.getFontMetrics().stringWidth(text)
      if (align == TextAlign.RIGHT)
        x0 -= w
      else
        x0 -= w/2f
    }
    g.drawString(text, x0, y.toFloat())
  }
  
  override fun textWidth(s: String): Double {
    return g.getFontMetrics().stringWidth(s).toDouble()
  }

  override fun beginShape() { path = Path2D.Double() }
  override fun moveTo(x: Double, y: Double) { path?.moveTo(x, y) }
  override fun lineTo(x: Double, y: Double) { path?.lineTo(x, y) }
  override fun closePath() { path?.closePath() }
  override fun drawShape(s: DrawStyle) { draw(s, path!!) }

  override fun translate(x: Double, y: Double) { g.translate(x, y) }
  override fun rotate(degrees: Double) { g.rotate(toRadians(degrees)) }
  override fun scale(sx: Double, sy: Double) { g.scale(sx, sy) }

  override fun save() {
    ctm.add(g.transform)
  }
  override fun restore() {
    if (ctm.isEmpty())
      throw IllegalArgumentException("Restore without save")
    g.transform = ctm.removeAt(ctm.lastIndex)
  }

  fun done() { g.dispose() }
}

// --------------------------------------------------------------------
