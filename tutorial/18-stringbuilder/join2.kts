import java.lang.System.currentTimeMillis

fun join(l: List<Int>): String {
  var s = ""
  for (e in l) {
    if (s.isEmpty())
      s = e.toString()
    else
      s = s + ", " + e.toString()
  }
  return s
}

val n = args[0].toInt()
val a = (1 .. n).toList()

val t0 = currentTimeMillis()
val s = join(a)
val t1 = currentTimeMillis()

println("Creating a string with ${a.size} integers took ${t1 - t0} milliseconds")
