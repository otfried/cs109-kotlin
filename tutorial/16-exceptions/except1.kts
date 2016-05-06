fun readString(prompt: String): String {
  print(prompt)
  System.out.flush()
  return readLine() ?: ""
}

fun f(n: Int) {
  println("Starting f($n) ... ")
  g(n)
  println("Ending f($n) ... ")
}

fun g(n: Int) {
  println("Starting g($n) ... ")
  val m = 100 / n
  println("The result is $m")
  println("Ending g($n) ... ")
}

fun main() {
  while (true) {
    val s = readString("Enter a number> ")
    if (s == "")
      return
    try {
      println("Beginning of try block")
      val n = s.toInt()
      f(n)
      println("End of try block")
    }
    catch (e: NumberFormatException) {
      println("Please enter a number!")
    }
    catch (e: ArithmeticException) {
      println("I can't handle this value!")
    }
  }
}

main()
