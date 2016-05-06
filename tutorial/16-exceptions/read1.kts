val fd = java.io.File("project.txt")

try {
  fd.forEachLine {
    println("${it.length} $it")
  }
} 
catch (e: java.io.FileNotFoundException) {
  println("Project file does not exist!")
}
catch (e: java.io.IOException) {
  println("Error reading project file!")
}
