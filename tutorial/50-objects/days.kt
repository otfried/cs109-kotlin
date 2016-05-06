
val digits = "0123456789"

// if s is not a legal date, or is not in range, 
// then throws IllegalArgumentException
fun getDate(s: String): Date {
  if (s.length != 10 || s[4] != '/' || s[7] != '/')
    throw IllegalArgumentException()
  for ((i, ch) in s.withIndex()) {
    if (i != 4 && i != 7 && ch !in digits)
      throw IllegalArgumentException()
  }
  val year = s.substring(0, 4).toInt()
  val month = s.substring(5, 7).toInt()
  val day = s.substring(8).toInt()
  return Date(year, month, day)
}

fun main(args: Array<String>) {
  try {
    if (args.size == 1) {
      val d = getDate(args[0])
      println("$d is a ${d.dayOfWeek()}")
    } else if (args.size == 2) {
      val d1 = getDate(args[0])
      val d2 = getDate(args[1])
      println("There are ${d2 - d1} days between $d1 and $d2")
    } else if (args.size == 3) {
      val d1 = getDate(args[0])
      val sign = if (args[1] == "-") -1 else +1
      val dist = args[2].toInt()
      val d2 = d1 + sign * dist
      println("$d1 ${args[1]} $dist days = $d2")
    } else {
      System.err.println("Must have one, two, or three arguments")
    }
  }
  catch (e: NumberFormatException) {
    System.err.println("Illegal number")
  }
  catch (e: IllegalArgumentException) {
    System.err.println("Illegal date")
  }
}
