fun opinion(x: Int) {
  when (x) {
    0, 1 -> println("A binary digit")
    in 0..9 -> println("A decimal digit")
    in setOf(16, 25, 36, 49, 64, 81, 100) -> println("A square")
    !in 0..100 -> println("Not a two-digit number")
  }
}
