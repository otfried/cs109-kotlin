
fun sieve(n: Int): Set<Int> {
  var s = (2 .. n).toMutableSet()
  val sqrtn = Math.sqrt(n.toDouble()).toInt()
  for (i in 2 .. sqrtn) {
    if (i in s) {
      var k = i * i
      while (k <= n) {
      	s.remove(k)
	k += i
      }
    }
  }
  return s
}
  
val num = if (args.size == 1) args[0].toInt() else 1000

val primes = sieve(num)

for (i in primes)
  print("$i ")
  
println()
