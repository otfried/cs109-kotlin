
data class Point(val x: Int, val y: Int, val color: String)

fun more(l: Array<Point>) {
  for (i in l.indices) {
    l[i] = Point(l[i].y, l[i].x, l[i].color)
  }
  // HERE
}

fun test(m: Int, s: String) {
  val a = Point(1, m, s)
  val b = arrayOf(a, Point(2, 7, "blue"))
  val c = b[1]
  more(b)
}
