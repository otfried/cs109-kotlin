import org.otfried.cs109.readString

val choice = readString("What's your favorite color? ").trim()
when (choice) {
  "orange" -> println("Here's an orange")
  "green" -> println("It greens so green in Spain")
  "gray", "grey", "black" -> println("Huh?  That's not a color.")
  "red" -> println("Roses are red")
  "pink" -> println("Pretty in pink")
  "blue" -> println("Are you feeling blue?")
  "yellow" -> println("Goodbye yellow-brick road")
  else -> println("That's an uncommon answer")
}
