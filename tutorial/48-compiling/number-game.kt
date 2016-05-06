var secret = 0

val random = java.util.Random()

fun readString(prompt: String): String {
  print(prompt)
  System.out.flush()
  return readLine() ?: ""
}

fun answerGuess(guess: Int) {
  if (guess == secret)
    println("You got it")
  else if (guess > secret)
    println("Too big")
  else if (guess < secret)
    println("Too small")
}

fun main(args: Array<String>) {
  secret = random.nextInt(100)
  var guess = -1
  while (guess != secret) {
    guess = readString("Guess my number> ").toInt()
    answerGuess(guess)
  }
}
