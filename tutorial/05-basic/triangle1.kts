fun triangle(n: Int) {
  for (i in 1 .. n) {
    for (j in 1 .. i)  
      print("*")
    println()
  }
}

val size = 5
triangle(size)
