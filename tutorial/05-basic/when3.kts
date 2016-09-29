import org.otfried.cs109.readString

val choice = readString("What's your favorite color? ").trim()
var reddish = false
when (choice) {
  "orange" -> { println("Here's an orange"); reddish = true }
  "green" -> println("It greens so green in Spain")
  "gray", "grey", "black" -> println("Huh?  That's not a color.")
  "red" -> { println("Roses are red"); reddish = true }
  "pink" -> { println("Pretty in pink"); reddish = true }
  "blue" -> println("Are you feeling blue?")
  "yellow" -> println("Goodbye yellow-brick road")
  "white" -> { /* do nothing */ }
  else -> println("That's an uncommon answer")
}
if (reddish)
   println("Another lover of reddish tones")
