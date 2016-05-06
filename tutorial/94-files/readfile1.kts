fun readFile(fname: String) {
  val reader = java.io.File(fname).bufferedReader()
  var line = reader.readLine()
  while (line != null) {
    println(line)
    line = reader.readLine()
  }
  reader.close()
}

readFile("text.txt")
