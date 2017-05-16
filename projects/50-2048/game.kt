//
// The 2048 game, terminal version
//

import org.otfried.cs109.readString

fun main(args: Array<String>) {
  val b = Board()
  b.insert()
  b.insert()

  var points = 0
  
  while (true) {
    println(b)
    println("$points points\n")
    val s = readString("What is your move: ").toLowerCase().trim()
    println()
    if (s.length == 1 && (s in "lrud")) {
      points += b.push(s[0])
      if (b.isFull()) {
	println(b)
	println("\nGame over.")
	println("You have $points points.")
	return
      }
      b.insert()
    }
  }
}

