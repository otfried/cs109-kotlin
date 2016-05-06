fun reverser() {
  var line: String? = readLine()
  while (line != null) {
    println(line.reversed())
    line = readLine()
  }
}

println("Enter lines to be reversed:")
reverser()
