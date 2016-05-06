
val K = 100 // number of intervals to approximate

fun integrate(a: Double, b: Double, f: (Double) -> Double): Double {
  require(b > a)
  val delta = (b - a)/ K
  var total = 0.0
  var x = a
  for (i in 1 .. K) {
    total += (f(x) + f(x+delta)) * 0.5 * delta
    x += delta
  }
  return total
}
