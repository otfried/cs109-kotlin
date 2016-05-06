val Suits = arrayOf("Clubs", "Spades", "Hearts", "Diamonds")
val Faces = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", 
		    "Jack", "Queen", "King", "Ace")

data class Card(val face: String, val suit: String) {
  init {
    require(suit in Suits)
    require(face in Faces)
  }

  override fun toString(): String {
    val a = if (face == "Ace" || face == "8") "an " else "a "
    return a + face + " of " + suit
  }

  fun value(): Int = when(face) {
    "Ace" -> 11
    "Jack" -> 10
    "Queen" -> 10
    "King" -> 10
    else -> face.toInt()
  }
}

fun main(args: Array<String>) {
  val c1 = Card("Ace", "Diamonds")
  val c2 = Card("Jack", "Spades")
  val c3 = Card("8", "Hearts")

  val hand = listOf(c1, c2, c3)

  for (c in hand)
    println("Card $c has value ${c.value()}")
}
