val fname = "words.txt"

val words = java.io.File("words.txt").useLines { it.toSet() }

fun readString(prompt: String): String {
  print(prompt)
  System.out.flush()
  return readLine() ?: ""
}

while (true) {
  val w = readString("Enter a word> ").trim()
  if (w == "")
    break
  if (w in words) 
    println("$w is a word")
  else
    println("Error: $w is not a word")
}
