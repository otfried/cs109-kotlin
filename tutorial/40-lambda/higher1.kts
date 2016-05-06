fun fact(x: Int): Int {
  var f = 1
  for (i in 1 .. x)
    f *= i
  return f
}

fun sumInt(a: Int, b: Int): Int {
  var s = 0
  for (i in a .. b)
    s += i
  return s
}

fun sumCubes(a: Int, b: Int): Int {
  var s = 0
  for (i in a .. b)
    s += i * i * i
  return s
}

fun sumFact(a: Int, b: Int): Int {
  var s = 0
  for (i in a .. b)
    s += fact(i)
  return s
}

