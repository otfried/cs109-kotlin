class Accumulator {
  private var current = 0
  fun add(n: Int) {
    current += n
  }
  fun sum(): Int = current
}

var acc1 = Accumulator()
acc1.add(7)
//acc1.current = 0
acc1.add(13)
println(acc1.sum())

