fun factorial(n: Int): Long {
  require(n >= 2)
  assert(false)
  var result = 1L
  for (i in 1 .. n)
    result *= i
  return result
}

println(factorial(args[0].toInt()))

