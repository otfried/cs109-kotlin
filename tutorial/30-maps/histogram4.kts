import kotlin.comparisons.compareBy
import kotlin.comparisons.thenBy

fun histogram(fname: String): Map<String, Int> {
  val file = java.io.File(fname)
  val hist = mutableMapOf<String, Int>()

  file.forEachLine {
  line ->		   
    if (line != "") {
      val words = line.split(Regex("[ ,:;.?!()-]+"))
      for (word in words) {
	val upword = word.toUpperCase()
	val count = hist[upword] ?: 0
	hist[upword] = count + 1
      }
    }
  }
  return hist
}

fun printHistogram(h: Map<String, Int>) {
  val s = h.toSortedMap(compareBy<String> { -h[it]!! }.thenBy { it })
  for ((word, count) in s)
    println("%20s: %d".format(word, count))
}

if (args.size != 1) {
  println("Usage: kotlinc -script histogram1.kts <file name>")
  kotlin.system.exitProcess(1)
}
  
val fname = args[0]
val hist = histogram(fname)
printHistogram(hist)
