import org.otfried.cs109.readString

val choice = readString("What's your favorite color? ").trim()
val response = when (choice) {
    	         "orange" -> "Here's an orange"
	       	 "green" -> "It greens so green in Spain"
		 "gray", "grey", "black" -> "Huh?  That's not a color."
		 "red" -> "Roses are red"
		 "pink" -> "Pretty in pink"
		 "blue" -> "Are you feeling blue?"
		 "yellow" -> "Goodbye yellow-brick road"
		 else -> "That's an uncommon answer"
	       }
println(response)
