import org.otfried.cs109.readString

fun f(n: Int) {
  println("Starting f($n) ... ")
  g(n)
  println("Ending f($n) ... ")
}

fun g(n: Int) {
  println("Starting g($n) ... ")
  if (n < 0)
    throw IllegalArgumentException()
  println("The value is $n")
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
    catch (e: IllegalArgumentException) {
      e.printStackTrace()
    }
  }
}

main()
