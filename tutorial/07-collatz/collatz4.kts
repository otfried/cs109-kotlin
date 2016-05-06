
fun next(n: Int): Int = 
  if (n % 2 == 0)
    n / 2
  else
    3 * n + 1

fun collatz(n0: Int) {
  var n = n0
  while (n != 1) {
    print(n)
    print(" ")
    n = next(n)
  }
  println(1)
}

fun collatzCount(n0: Int): Int {
  var n = n0
  var count = 0
  while (n != 1) {
    n = next(n)
    count += 1
  }
  return count
}

fun findMax(n: Int) {
  var maxCount = 0
  var maxStart = 1
  for (i in 2 .. n) {
    val count = collatzCount(i)
    if (count > maxCount) {
      maxCount = count
      maxStart = i
    }
  }
  println("Starting at $maxStart needs $maxCount steps.")
}

fun collatzBounded(n0: Int, steps: Int): Int {
  var n = n0
  var count = 0
  while (n != 1 && count < steps) {
    n = next(n)
    count += 1
  }
  return count
}

fun findLong(n: Int, steps: Int) {
  for (i in 2 .. n) {
    val count = collatzBounded(i, steps)
    if (count >= steps) { 
      println("Starting at $i needs $count steps.")
    }
  }
}
