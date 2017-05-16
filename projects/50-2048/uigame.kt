//
// The 2048 game, UI version
//

import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.Canvas
import org.otfried.cs109.TextAlign
import org.otfried.cs109ui.*
import java.awt.image.BufferedImage

val backgroundColor = Color(0xbbada0)

val tileColors = mapOf(0 to Color(205, 192, 180),
		     2 to Color(0xeee4da),
		     4 to Color(0xede0c8),
		     8 to Color(0xf2b179),
		     16 to Color(0xf59563),
		     32 to Color(0xf67c5f),
		     64 to Color(0xf65e3b), 
		     128 to Color(0xedcf72),
		     256 to Color(0xedcc61),
		     512 to Color(0xedc850),
		     1024 to Color(0xedc53f),
		     2048 to Color(0xedc22e))
val otherTileColor = Color(0x3c3a32)

val lightTextColor = Color(119, 110, 101)
val darkTextColor = Color(0xf9f6f2)

fun textColor(tileValue: Int) = 
  if (tileValue <= 4) lightTextColor else darkTextColor

fun textSize(tileValue: Int) = 
  if (tileValue <= 64) 55.0
  else if (tileValue <= 512) 45.0
  else if (tileValue <= 2048) 35.0
  else 30.0

fun drawBoard(b: Board, g: Canvas) {
  g.clear(Color(0xbbada0))
  for (row in 0 .. 3) {
    for (col in 0 .. 3) {
      val tile = b.cell(row, col)
      g.setColor(tileColors[tile] ?: otherTileColor)
      g.drawRectangle(15.0 + 121.0 * col, 15.0 + 121.0 * row, 107.0, 107.0)
      if (tile > 0) {
	g.setColor(textColor(tile))
	g.setFont(textSize(tile))
	val s = tile.toString()
	val w = g.textWidth(s)
	g.drawText(s, 15.0 + 121.0 * col + 53.0 - w/2, 
	           15.0 + 121.0 * row + 70.0)
      }
    }
  }
}

fun main(args: Array<String>) {
  val b = Board()
  b.insert()
  b.insert()

  var points = 0
  
  setTitle("2048 - $points points")
  val image = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
  val canvas = ImageCanvas(image)

  while (true) {
    drawBoard(b, canvas)
    show(image)

    val ch = waitKey()
    if (ch in "lrud") {
      points += b.push(ch)
      setTitle("2048 - $points points")
      if (b.isFull()) {
	canvas.setColor(Color.RED)
	canvas.setFont(64.0)
	canvas.drawText("GAME OVER", 250.0, 250.0, TextAlign.CENTER)
	show(image)
	waitKey()
	close()
	return
      }
      b.insert()
    }
  }
}
