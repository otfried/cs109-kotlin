import org.otfried.cs109.readString

// Compute the value of a hand of cards.
fun handValue(hand: List<Card>): Int {
  var value = 0
  for (card in hand)
    value += card.value()
  return value
}

/* Display the text prompt and let's the user enter a string.  If the
  user enters "y", the function returns "true", and if the user enters
  "n", the function returns "false" If the user enters anything else,
  the function prints "I beg your pardon!", and asks again, repeating
  this until the user has entered a correct string.  */
fun askYesNo(prompt: String): Boolean { 
  while (true) {
    val user_input = readString(prompt)
    when(user_input) {
      "y" -> return true
      "n" -> return false
      else -> println("I beg your pardon!")
    }
  }
}

// Play one round of Blackjack
//  Returns 1 if player wins, -1 if dealer wins, and 0 for a tie.
fun blackjack(): Int {
  val deck = Deck()

  // initial cards
  var player = mutableListOf(deck.draw())
  println("You are dealt " + player.first())
  var dealer = mutableListOf(deck.draw())
  println("Dealer is dealt a hidden card")
    
  player.add(deck.draw())
  println("You are dealt " + player.last())
  dealer.add(deck.draw())
  println("Dealer is dealt " + dealer.last())
  println("Your total is ${handValue(player)}")

  // player's turn to draw cards
  var want = true
  while (want && handValue(player) < 21) {
    want = askYesNo("Would you like another card? (y/n) ")
    if (want) {
      player.add(deck.draw())
      println("You are dealt " + player.last())
      println("Your total is ${handValue(player)}")
  
      // if the player's score is over 21, the player loses immediately.
      if (handValue(player) > 21) {
	println("You went over 21! You lost!")
	return -1
      }
    }
  }
  
  println("The dealer's hidden card was " + dealer.first())
  while (handValue(dealer) < 17) {
    dealer.add(deck.draw())
    println("Dealer is dealt " + dealer.last())
  }
  println("The dealer's total is ${handValue(dealer)}")
  
  // summary
  val player_total = handValue(player)
  val dealer_total = handValue(dealer)
  println("\nYour total is $player_total")
  println("The dealer's total is $dealer_total")

  if (dealer_total > 21) {
    println("The dealer went over 21! You win!")
    return 1
  } else if (player_total > dealer_total) {
    println("You win!")
    return 1
  } else if (player_total < dealer_total) {
    println("You lost!")
    return -1
  } else {
    println("You have a tie!")
    return 0
  }
}

fun gameLoop() {
  println("Welcome to Blackjack!")
  while (true) {
    println()
    blackjack()    
    if (!askYesNo("\nPlay another round? (y/n) "))
      return
  }
}

fun main(args: Array<String>) {
  gameLoop()
}

