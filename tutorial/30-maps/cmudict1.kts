
fun readPronounciations(): Map<String,String> {
  val file = java.io.File("cmudict.txt")
  var m = mutableMapOf<String, String>()
  file.forEachLine {
    l ->
      if (l[0].isLetter()) {
        val p = l.trim().split(Regex("\\s+"), 2)
        val word = p[0].toLowerCase()
        if (!("(" in word))
	  m[word] = p[1]
      }
  }
  return m
}

fun reverseMap(m: Map<String, String>): Map<String,Set<String>> {
  var r = mutableMapOf<String,MutableSet<String>>()
  for ((word, pro) in m) {
    val s = r.getOrElse(pro) { mutableSetOf<String>() }
    s.add(word)
    r[pro] = s
  }
  return r
}

fun showHomophones(k: Int) {
  val m = readPronounciations()
  var r = reverseMap(m)
  for ((pro, words) in r) {
    if (words.size >= k) {
      print("$pro (${words.size} words):")
      println("  " + words.joinToString(separator=" "))
    }
  }
}

fun findWords() {
  val m = readPronounciations()
  for ((word, pro) in m) {
    val ord = word.substring(1)
    if (pro == m[ord])
      println(word)
  }
}

fun solvePuzzle() {
  val m = readPronounciations()
  for ((word, pro) in m) {
    if (word.length > 2 && word[0] != word[1]) {
      val ord1 = word.substring(1)
      val ord2 = word[0] + word.substring(2)
      if (pro == m[ord1] && pro == m[ord2])
	println(word)
    }
  }
}
