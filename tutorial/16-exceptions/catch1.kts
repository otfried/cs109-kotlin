
fun readString(prompt: String?): String {
  if (prompt != null)
    print(prompt)
  System.out.flush()
  return readLine() ?: ""
}

val str = readString("Enter a number> ")

try {
  val x = str.toInt()
  println("You said: $x")
} 
catch (e: NumberFormatException) {
  println("'$str' is not a number")
}
