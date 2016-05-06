fun test(s: String): Int = (s.toDouble() * 100).toInt()

fun show(s: String) {
  try {
    println(test(s))
  }
  catch (e: NumberFormatException) {
    println("Incorrect input")
  }
}
