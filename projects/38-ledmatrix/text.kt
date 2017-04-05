//
// LED matrix animation: scrolling text
// 

import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import java.awt.image.BufferedImage

// --------------------------------------------------------------------

// Put the student ids of all members of your team in the following list.
// It is enough if one of you submits the file.

val authors = listOf(20169999, 20168888)

// --------------------------------------------------------------------

// global variables to control the animation

var x: Int = 0
val message = "CS109 is fun"

// --------------------------------------------------------------------

// setup() is called once to set up your animation:

fun setup() {
  // text will come in from the right
  x = 32
}

// --------------------------------------------------------------------

// loop() is called in regular intervals to compute the next frame
// of the animation.
// The argument leds is a bitmap of size 32 x 16.
// loop() needs to draw the next frame of the animation on this bitmap.
// Black means LED off, anything else means LED on.
// The bitmap is already cleared to black before loop() is called.
// If loop() returns true, then the animation ends.

fun loop(leds: BufferedImage): Boolean {
  val g = ImageCanvas(leds)
  g.setFont(16.0, "SansSerif")
  val wid = g.textWidth(message)
  g.setColor(Color.RED)
  g.drawText(message, x.toDouble(), 14.0)
  g.done()

  // after drawing the current image, update global variables
  x -= 1

  // indicate whether the animation is finished
  return (x < -wid)
}

// --------------------------------------------------------------------
