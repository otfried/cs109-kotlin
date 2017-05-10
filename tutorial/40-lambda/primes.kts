val n = args[0].toInt()
val sqrtn = Math.sqrt(n.toDouble()).toInt()

var s = (2 .. n).toList()

while (s.first() <= sqrtn) {
  val k = s.first()
  print("$k ")
  s = s.filter { it % k != 0 }
}

println(s.joinToString(separator=" "))
