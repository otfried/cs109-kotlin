//
// LED matrix animation: moving square
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
var rounds: Int = 0

// --------------------------------------------------------------------

// setup() is called once to set up your animation:

fun setup() {
  // square will come in from the right
  x = 32
  // count how often square scrolled by
  rounds = 0 
}

// --------------------------------------------------------------------

// loop() is called in regular intervals to compute the next frame
// of the animation.
// The argument image is a bitmap of size 32 x 16.
// loop() needs to draw the next frame of the animation on this bitmap.
// Black means LED off, anything else means LED on.
// If loop() returns true, then the animation ends.

fun loop(image: BufferedImage): Boolean {
  // You can draw on image any way you like.
  // You can use "setRGB" to set pixels directly,
  // or you could use an ImageCanvas like this:
  val g = ImageCanvas(image)
  g.clear(Color.BLACK)
  g.setColor(Color.RED)
  g.drawRectangle(x.toDouble(), 3.0, 10.0, 10.0)
  g.done()

  // after drawing the current image, update global variables
  x -= 1
  if (x < -10) {
    x = 32 // start again on right edge
    rounds += 1
  }

  // indicate whether the animation is finished
  return (rounds >= 4)
}

// --------------------------------------------------------------------
