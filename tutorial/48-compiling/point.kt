data class Point(val x: Int, val y: Int) {
  override fun toString(): String = 
    "[%d,%d]".format(x,y)
}
