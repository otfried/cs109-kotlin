
data class Point(val x: Int, val y: Int) {
  override fun toString(): String = "(%d, %d)".format(x, y)
}

data class Rect(var corner: Point, var width: Int, var height: Int) {
  init {
    require(width > 0 && height > 0)
  }
  override fun toString(): String = 
    "[%d ~ %d, %d ~ %d]".format(corner.x, corner.x + width, 
				corner.y, corner.y + height)
}
