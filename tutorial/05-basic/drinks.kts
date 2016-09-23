
import org.otfried.cs109.readString

var name = readString("What's your name? ").trim()
var answer = readString("Hi $name, would you like a beer? ").trim().toLowerCase()
if (answer == "yes" || answer == "y") {
   var num = readString("How many beer will you have? ").toInt()
   if (num > 5)
      println("That sounds a bit excessive!")
   else if (num < 2)
      println("Don't be shy...")
   else
      println("Here you are.")
}
