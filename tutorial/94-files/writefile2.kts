java.io.File("test.txt").printWriter().use {
  out ->
    for (i in 1 .. 10)
      out.println("$i: ${i * i}")
}
