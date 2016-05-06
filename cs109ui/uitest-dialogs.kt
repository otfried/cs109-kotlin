import org.otfried.cs109ui.*

fun main(args: Array<String>) {
  setTitle("CS109 UI Dialog Test")

  while (true) {
    showMessage("This is a message")

    val yesno = askYesNo("Do you like this?")
    println("Answer: $yesno")

    val name = inputString("What is your name?")
    println("Name: $name")

    val drink = askChoice("What do you like best",
    	                  arrayOf("Beer", "Wine", "Makkoli", "Soju", "Water"))
    println("Drink: $drink")

    val choice = askButtons("What do you want to do now?",
		            arrayOf("Exit", "More dialogs", "Nothing"))
    println("Do next: $choice")
    when(choice) {
      1 -> close()
      2 -> { /* nothing */ }
      3 -> return
    }
  }
}
