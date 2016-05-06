fun histogram(fname: String): Map<String, Int> {
  val file = java.io.File(fname)
  val hist = mutableMapOf<String, Int>()

  file.forEachLine {
    if (it != "") {
      val words = it.split(Regex("[ ,:;.?!<>()-]+"))
      for (word in words) {
      	if (word == "") continue
	val upword = word.toUpperCase()
	hist[upword] = hist.getOrElse(upword) { 0 } + 1
      }
    }
  }
  return hist
}

fun printHistogram(h: Map<String, Int>) {
  val s = h.toSortedMap()
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
