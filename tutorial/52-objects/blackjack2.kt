
val Suits = arrayOf("Clubs", "Spades", "Hearts", "Diamonds")
val Faces = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", 
		    "Jack", "Queen", "King", "Ace")

val random = java.util.Random()

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

class Deck {
  private val cards = mutableListOf<Card>()

  init {
    generateDeck()
    shuffleDeck()
  }  
  
  private fun generateDeck() {
    for (suit in Suits) {
      for (face in Faces) {
	cards.add(Card(face, suit))
      }
    }
  }

  private fun shuffleDeck() {
    for (i in 1 .. 52) {
      // 0..i-2 already shuffled
      val j = random.nextInt(i)
      val cj = cards[j]
      cards[j] = cards[i-1]
      cards[i-1] = cj
    }
  }

  fun draw(): Card {
    assert(!cards.isEmpty())
    return cards.removeAt(cards.lastIndex)
  }
}


fun main(args: Array<String>) {
  val deck = Deck()
  for (i in 1 .. 10)
    println(deck.draw())
}

