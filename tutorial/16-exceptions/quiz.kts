
fun g(s: String, n: Int) {
  if (n > 10)
    throw IllegalArgumentException()
  for (i in 1 .. n)
    println(s)
}
  
fun f(s: String) {
  val t = s + " World"
  g(t, 3)
}

fun main(m: Int) {
  try {
    val r = 13 * m
    f("Hello")
    println(r)
  }
  catch (e: IllegalArgumentException) {
    println("Oops, something was wrong")
  }
}

main(13)


