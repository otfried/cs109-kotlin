package org.otfried.cs109ui

import kotlin.concurrent.*
import java.awt.Color
import java.awt.Point
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.awt.event.WindowEvent
import java.awt.event.KeyEvent
import java.awt.event.ComponentEvent
import java.awt.event.MouseEvent
import javax.swing.JOptionPane as JOP

// --------------------------------------------------------------------

interface Result

data class Key(val ch: Char) : Result
data class Mouse(val x: Int, val y: Int) : Result
object TimeOut : Result

val timeOutChar = '\u0000'

// --------------------------------------------------------------------

private fun onEDT(op: () -> Unit) {
  javax.swing.SwingUtilities.invokeLater(op)
}

private fun onEDTWait(op: () -> Unit) {
  javax.swing.SwingUtilities.invokeAndWait(op)
}

private fun sendEvent(r: Result) {
  with (UI) {
    timer.stop()   // key or mouse event cancels timeout
    lock.withLock {
      queue.add(r)
      eventCondition.signal()
    }
  }
}

// --------------------------------------------------------------------

private class JCanvas: javax.swing.JComponent() {
  private var image: BufferedImage? = null

  init {
    setPreferredSize(Dimension(480, 320))
    setFocusable(true)
    setOpaque(true)
    setDoubleBuffered(false)

    addKeyListener(object: java.awt.event.KeyAdapter() {
      override fun keyPressed(e: KeyEvent) {
        sendEvent(Key(e.keyChar))
      }
    })
    addMouseListener(object: java.awt.event.MouseAdapter() {
      override fun mouseClicked(e: MouseEvent) {
        sendEvent(Mouse(e.x, e.y))
      }
    })
  }
  
  override fun paintComponent(gr: java.awt.Graphics) {
    val g = gr as java.awt.Graphics2D
    if (image == null) {
      val d = size
      g.setColor(Color.WHITE)
      g.fillRect(0,0, d.width, d.height)
    } else 
      g.drawImage(image, null, 0, 0)
  }

  fun setImage(im: BufferedImage) {
    if (image?.width != im.width || image?.height != im.height)
      image = BufferedImage(im.width, im.height, BufferedImage.TYPE_INT_RGB)
    image!!.setData(im.getData())
    setPreferredSize(Dimension(im.width, im.height))
  }
}

// --------------------------------------------------------------------

private class Frame(canvas: JCanvas): java.awt.Frame() {
  init {
   setTitle("CS109 UI")
   setResizable(false)
   add(canvas)
   pack()
   addWindowListener(object: java.awt.event.WindowAdapter() {
     override fun windowClosing(e: WindowEvent) {
       kotlin.system.exitProcess(0)
     }
   })
  }
}

// --------------------------------------------------------------------

private object UI {
  init {
    println("CS109 UI version 2017/04/03")
  }
  
  val canvas = JCanvas()
  val ui = Frame(canvas)
  val queue = java.util.concurrent.ConcurrentLinkedQueue<Result>()
  val lock = java.util.concurrent.locks.ReentrantLock()
  val eventCondition = lock.newCondition()
  val timer = javax.swing.Timer(1000,
      object : javax.swing.AbstractAction() {
        override fun actionPerformed(e : java.awt.event.ActionEvent) =
          sendEvent(TimeOut)
      })
}

fun setTitle(s: String) {
  onEDTWait { UI.ui.setTitle(s) }
}

fun show(image: BufferedImage) {
  onEDT {
    UI.canvas.setImage(image)
    val insets = UI.ui.insets
    UI.ui.setSize(Dimension(image.width + insets.left + insets.right, 
                            image.height + insets.top + insets.bottom))
    UI.ui.setVisible(true)
    UI.canvas.repaint()
  }
}

fun setTimeOut(ms: Int) {
  UI.timer.setRepeats(false)
  UI.timer.setInitialDelay(ms)
  UI.timer.restart()
}

fun waitEvent(): Result {
  return UI.lock.withLock {
    while (UI.queue.isEmpty()) {
      UI.eventCondition.await()
    }
    UI.queue.remove()
  }
}

fun waitKey(): Char {
  while (true) {
    val r = waitEvent()
    when(r) {
      is Key -> return r.ch
      is TimeOut -> return timeOutChar
      // is Mouse ->
    }
  }
}

fun waitMouse(): Pair<Int, Int> {
  while (true) {
    val r = waitEvent()
    when(r) {
      // is Key ->
      is Mouse -> return Pair(r.x, r.y)
      is TimeOut -> return Pair(-1, -1)
    }
  }
}

fun waitForMs(ms: Int) {
  Thread.sleep(ms.toLong())
}

fun close() {
  onEDTWait { UI.ui.setVisible(false) }
  kotlin.system.exitProcess(0)
}

// --------------------------------------------------------------------
// Dialogs
// --------------------------------------------------------------------

fun showMessage(msg: String) {
  onEDTWait {
    UI.ui.setVisible(true)
    JOP.showMessageDialog(UI.canvas, msg, UI.ui.title, JOP.PLAIN_MESSAGE)
  }
}

fun askYesNo(question: String): Boolean {
  var result: Boolean = false
  onEDTWait {
    UI.ui.setVisible(true)
    val r = JOP.showConfirmDialog(UI.canvas, question,
                       UI.ui.title, JOP.YES_NO_OPTION, JOP.PLAIN_MESSAGE)
    result = (r == JOP.YES_OPTION)
  }
  return result
}

fun inputString(msg: String): String {
  var result: String = ""
  onEDTWait {
    UI.ui.setVisible(true)
    val r = JOP.showInputDialog(UI.canvas, msg, UI.ui.title,
			        JOP.PLAIN_MESSAGE, null, null, "")
    if (r is String)
      result = r
  }
  return result
}

// up to three buttons are okay
fun askButtons(question: String, buttons: Array<String>): Int {
  var result: Int = 0
  onEDTWait {
    UI.ui.setVisible(true)
    val r = JOP.showOptionDialog(UI.canvas, question, UI.ui.title,
				 JOP.YES_NO_CANCEL_OPTION, JOP.PLAIN_MESSAGE,
				 null, buttons, null)
    if (r != JOP.CLOSED_OPTION)
      result = r + 1
  }
  return result
}

fun askChoice(msg: String, choices: Array<String>): String {
  var result: String = ""
  onEDTWait {
    UI.ui.setVisible(true)
    val r = JOP.showInputDialog(UI.canvas, msg, UI.ui.title,
			        JOP.PLAIN_MESSAGE, null,
			        choices, null)
    if (r is String)
      result = r
  }
  return result
}

// --------------------------------------------------------------------
