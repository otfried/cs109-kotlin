
// check if ch is a letter
fun isLetter(ch: Char) = ch in 'a'..'z' || ch in 'A'..'Z'

// shuffle(s) returns a randomly shuffled copy of the string s
fun shuffle(s: String): String {
  val l = s.toMutableList()
  java.util.Collections.shuffle(l)
  return l.joinToString("")
}

fun reorderLetters(s: String) {
  var i = 0
  while (i < s.length) {
    // modify this space
  }
  println()
}

if (args.size != 1)
  println("Usage: kts readable.kts <filename>")
else
  java.io.File(args[0]).forEachLine { reorderLetters(it) }
