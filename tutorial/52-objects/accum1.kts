class Accumulator {
  var sum = 0
  fun add(n: Int) {
    sum += n
  }
}

var acc1 = Accumulator()
acc1.add(7)
//acc1.sum = 0
acc1.add(13)
println(acc1.sum)
