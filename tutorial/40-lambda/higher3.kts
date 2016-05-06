
fun fact(x: Int): Int {
  var f = 1
  for (i in 1 .. x)
    f *= i
  return f
}

fun sum(a: Int, b: Int, f: (Int) -> Int): Int {
  var s = 0
  for (i in a..b) 
    s += f(i)
  return s
}

fun sumInt(a: Int, b: Int): Int = sum(a, b) { it }
fun sumCubes(a: Int, b: Int): Int = sum(a, b) { it * it * it }
fun sumFact(a: Int, b: Int): Int = sum(a, b) { fact(it) }

