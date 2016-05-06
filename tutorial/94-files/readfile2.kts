fun readFile(fname: String) {
  val reader = java.io.File(fname).bufferedReader()
  reader.use {
    reader ->
      var line = reader.readLine()
      while (line != null) {
        println(line)
        line = reader.readLine()
      }
  }
}

readFile("text.txt")
