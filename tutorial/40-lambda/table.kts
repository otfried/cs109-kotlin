
fun printTable(a: Int, b: Int, f: (Int) -> Int) {
  for (i in a .. b)
    println("%4d: %d".format(i, f(i)))
}

