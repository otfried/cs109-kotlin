//
// Main program for LED matrix
//

import org.otfried.cs109ui.*
import java.awt.image.BufferedImage
import org.otfried.cs109.Color
import java.lang.System.currentTimeMillis

fun showLeds(leds: BufferedImage, image: BufferedImage, cell: Int) {
  val g = ImageCanvas(image)
  g.clear(Color.BLACK)
  for (x in 0 until 32) {
    for (y in 0 until 16) {
      if ((leds.getRGB(x, y) and 0xffffff) != 0)
        g.setColor(Color.RED)
      else
        g.setColor(Color(0x300000))
      g.drawCircle((x + 1.5) * cell, (y + 1.5) * cell, 0.3 * cell)
    }
  }
  g.done()
  show(image)
}

fun performTick(leds: BufferedImage, image: BufferedImage,
                cell: Int, speed: Int): Boolean {
  val t0 = currentTimeMillis()
  // clear display    
  for (x in 0 until 32)
    for (y in 0 until 16)
      leds.setRGB(x, y, 0)
  val finished = loop(leds)
  showLeds(leds, image, cell)
  while (true) {
    val t1 = currentTimeMillis()
    val rest = speed - (t1 - t0)
    if (rest <= 5)
      return finished
    Thread.sleep(rest)
  }
}

fun main(args: Array<String>) {
  var cell = 32
  var speed = 300
  try {
    if (args.size in setOf(1, 2))
      cell = args[0].toInt()
    if (args.size == 2)
      speed = args[1].toInt()
  }
  catch (e: NumberFormatException) {
    println("Usage: kt LedmatrixKt <led-size> <speed>")
    return
  }

  println("LED Matrix project by ${authors.joinToString(" and ")}")
      
  setTitle("CS109 LED Matrix")

  val leds = BufferedImage(32, 16, BufferedImage.TYPE_INT_RGB)
  val image = BufferedImage(34 * cell, 18 * cell, BufferedImage.TYPE_INT_RGB)
  
  setup()
  var finished = false
  while (!finished)
    finished = performTick(leds, image, cell, speed)
  close() // close window and terminate program
}
