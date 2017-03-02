fun triangle(n: Int) {
  for (i in 1 .. n) {
    for (j in 1 .. i)  
      print("*")
    println()
  }
}

if (args.size == 1) {
  val size = args[0].toInt()
  triangle(size)
} else
  println("Usage: kts triangle3.kts <size>")

